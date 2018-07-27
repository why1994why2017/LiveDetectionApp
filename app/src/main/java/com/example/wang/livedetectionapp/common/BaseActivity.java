package com.example.wang.livedetectionapp.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Description: 所有的Activity都要继承与这个类
 *              能进行统一管理
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String msg = "========== " + this.getLocalClassName() + ".onCreate()" + " ==========";
        LogUtil.i("LiveDetectionApp", msg);

        //插入进列表
        AppUtil.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String msg = "========== " + this.getLocalClassName() + ".onResume()" + " ==========";
        LogUtil.i("LiveDetectionApp", msg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String msg = "========== " + this.getLocalClassName() + ".onDestroy()" + " ==========";
        LogUtil.i("LiveDetectionApp", msg);

        //删除列表中的当前Activity
        AppUtil.finishActivity(this);

    }
}
