package com.example.wang.livedetectionapp;

import android.os.Bundle;

import com.example.wang.livedetectionapp.Database.DatabaseManager;
import com.example.wang.livedetectionapp.common.AppUtil;
import com.example.wang.livedetectionapp.common.BaseActivity;

public class FirstPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        DatabaseManager.createDatabase(this);

        String tempLogin = DatabaseManager.getTempLogin();

        if (tempLogin == null) {
            MainActivity.startActivity(this);
            AppUtil.finishCurrentActivity();

        } else {
            IndexUIActivity.startActivity(this);
            AppUtil.finishCurrentActivity();

        }
    }

}
