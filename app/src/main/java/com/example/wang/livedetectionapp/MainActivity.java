package com.example.wang.livedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.example.wang.livedetectionapp.Database.DatabaseManager;
import com.example.wang.livedetectionapp.common.AppUtil;
import com.example.wang.livedetectionapp.common.BaseActivity;
import com.example.wang.livedetectionapp.common.LogUtil;
import com.example.wang.livedetectionapp.mode.PersonInfo;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private PersonInfo mPersonInfo;

    private EditText mLoginTest;
    private EditText mPasswordTest;
    private Button mLoginButton;
    private TextView mRegisterButton;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, PersonInfo personInfo) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("person_info", personInfo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPersonInfo = getIntent().getParcelableExtra("person_info");

        initView();

    }

    private void initView() {
        mLoginTest = findViewById(R.id.main_login_text);
        mPasswordTest = findViewById(R.id.main_password_text);
        mLoginButton = findViewById(R.id.login_button);
        mRegisterButton = findViewById(R.id.main_register_button);

        mLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:{
                if (mLoginTest.getText().toString() == null || mPasswordTest.getText().toString() == null) {
                    break;
                }
                //LogUtil.e("------------------------>", ""+mPersonInfo.getLoginName()+"--------------------->"+mPersonInfo.getPassword());
                if (mPersonInfo != null) {
                    if (mLoginTest.getText().toString().equals(mPersonInfo.getLoginName())
                            && mPasswordTest.getText().toString().equals(mPersonInfo.getPassword())) {
                        IndexUIActivity.startActivity(this);
                        AppUtil.finishCurrentActivity();
                    }
                } else {
                    PersonInfo personInfo = DatabaseManager.getPersonInfo();
                    if (personInfo != null) {
                        if (mLoginTest.getText().toString().equals(personInfo.getLoginName())
                                && mPasswordTest.getText().toString().equals(personInfo.getPassword())) {
                            IndexUIActivity.startActivity(this);
                            AppUtil.finishCurrentActivity();
                        }
                    }
                }
                break;
            }

            case R.id.main_register_button: {
                RegisterActivity.startActivity(this);
                AppUtil.finishCurrentActivity();
                break;
            }

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppUtil.finishAllActivity();
        }
        return false;
    }
}
