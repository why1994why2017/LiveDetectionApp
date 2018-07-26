package com.example.wang.livedetectionapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wang.livedetectionapp.Database.MyDatabaseHelper;

public class FirstPageActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private String templogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        dbHelper = new MyDatabaseHelper(this, "InfoStore.db", null, 2);
        /*Button createDatabase = findViewById(R.id.firstpage_create_button);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbHelper.getWritableDatabase();
            }
        });*/
        //dbHelper.getWritableDatabase();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                templogin = cursor.getString(cursor.getColumnIndex("templogin"));
                if (templogin != null)
                    break;
                //Log.d("FirstPageActivity", "------------>" + templogin);
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (templogin == null){
            MainActivity.startActivity(this);
            finish();
        }else {
            IndexUIActivity.startActivity(this);
            finish();
        }
    }
}
