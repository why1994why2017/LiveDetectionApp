package com.example.wang.livedetectionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String mLogin;
    public static String mPassword;

    private EditText mLoginTest;
    private EditText mPasswordTest;
    private Button mLoginButton;
    private TextView mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            case R.id.login_button:
                if (mLogin == null || mPassword == null) {
                    break;
                }
                if (mLogin.equals(mLoginTest.getText().toString()) && mPassword.equals(mPasswordTest.getText().toString())) {
                    IndexActivity.startActivity(this);
                }
                break;
            case R.id.main_register_button:
                RegisterActivity.startActivity(this);
                break;
            default:
                break;
        }
    }
}
