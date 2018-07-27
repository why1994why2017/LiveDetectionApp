package com.example.wang.livedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.wang.livedetectionapp.Database.MyDatabaseHelper;
import com.example.wang.livedetectionapp.common.BaseActivity;

public class InformationActivity extends BaseActivity implements View.OnClickListener{

    private Button mLogoutButton;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);

        initView();
    }

    private void initView() {
        mLogoutButton = findViewById(R.id.information_logout_button);
        mLogoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.information_logout_button:
                MainActivity.startActivity(this);
                break;
            default:
                break;
        }
    }

}
