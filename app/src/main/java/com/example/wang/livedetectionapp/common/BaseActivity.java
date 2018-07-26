package com.example.wang.livedetectionapp.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String msg = "========== " + this.getClass().getName() + " ==========";

        LogUtil.e("LiveDetectionApp", msg);

        //插入进列表
        AppManager.addActivity(this);
    }
}
