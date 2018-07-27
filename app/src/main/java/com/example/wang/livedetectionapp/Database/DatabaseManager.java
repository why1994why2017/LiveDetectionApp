package com.example.wang.livedetectionapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wang.livedetectionapp.mode.PersonInfo;

/**
 *  Description: 数据库的管理操作
 */

public class DatabaseManager {

    private static final int DATABASE_VERSION = 1;

    //全局只应该保留一个，为了防止多线程死锁。不要close
    private static MyDatabaseHelper mDatabaseHelper;
    private static SQLiteDatabase db;

    public static void createDatabase(Context context) {
        if (mDatabaseHelper == null && db == null) {
            mDatabaseHelper = new MyDatabaseHelper(context, "InfoStore.db", null, DATABASE_VERSION);
            db = mDatabaseHelper.getWritableDatabase();
        }
    }

    public static String getTempLogin() {

        String tempLogin = null;

        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                tempLogin = cursor.getString(cursor.getColumnIndex("temp_login"));
                if (tempLogin != null)
                    break;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return tempLogin;
    }

    public static void insertPersonInfo(PersonInfo personInfo) {

        ContentValues values = new ContentValues();
        values.put("login_name", personInfo.getLoginName());
        values.put("passwords", personInfo.getPassword());
        values.put("person_name", personInfo.getPersonName());
        values.put("gender", personInfo.getGender());
        db.insert("info", null, values);
        values.clear();
    }

    public static PersonInfo getPersonInfo() {

        PersonInfo personInfo = new PersonInfo();

        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                personInfo.setLoginName(cursor.getString(cursor.getColumnIndex("login_name")));
                personInfo.setPassword(cursor.getString(cursor.getColumnIndex("passwords")));
                personInfo.setPersonName(cursor.getString(cursor.getColumnIndex("person_name")));
                personInfo.setGender(cursor.getString(cursor.getColumnIndex("gender")));

                if (personInfo.getLoginName() == null || personInfo.getPassword() == null) {
                    return null;
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        return personInfo;
    }

}
