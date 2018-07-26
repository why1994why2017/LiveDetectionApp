package com.example.wang.livedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wang.livedetectionapp.Database.MyDatabaseHelper;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mLogoutButton;
    private MyDatabaseHelper dbHelper;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);

        initView();

        dbHelper = new MyDatabaseHelper(this, "InfoStore.db", null, 2);
    }

    private void initView() {
        mLogoutButton = findViewById(R.id.information_logout_button);
        mLogoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.information_logout_button:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //db.delete("info", "templogin", )
                String sql = "delete from info where id<>0";
                db.execSQL(sql);
                Intent intent = new Intent();
                //MainActivity.startActivity(this);
                intent.setClass(InformationActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
