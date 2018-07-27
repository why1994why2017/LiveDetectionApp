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

    public static String getTempLogin(Context context) {

        String tempLogin = null;
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, "InfoStore.db", null, 2);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                tempLogin = cursor.getString(cursor.getColumnIndex("tempLogin"));
                if (tempLogin != null)
                    break;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return tempLogin;
    }

    public static void insertPersonInfo(Context context, PersonInfo personInfo) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, "InfoStore.db", null, 2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("login_name", personInfo.getLoginName());
        values.put("passwords", personInfo.getPassword());
        values.put("name", personInfo.getPersonName());
        values.put("gender", personInfo.getGender());
        db.insert("info", null, values);
        values.clear();
    }

    public static PersonInfo getPersonInfo(Context context) {

        PersonInfo personInfo = new PersonInfo();
        MyDatabaseHelper dbHelper;
        dbHelper = new MyDatabaseHelper(context, "InfoStore.db", null, 2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                personInfo.setLoginName(cursor.getString(cursor.getColumnIndex("login_name")));
                personInfo.setPassword(cursor.getString(cursor.getColumnIndex("passwords")));
                personInfo.setLoginName(cursor.getString(cursor.getColumnIndex("name")));
                personInfo.setGender(cursor.getString(cursor.getColumnIndex("gender")));

                if (personInfo.getLoginName() != null && personInfo.getPassword() != null)
                    return null;

            } while (cursor.moveToNext());
        }
        cursor.close();

        return personInfo;

    }

}
