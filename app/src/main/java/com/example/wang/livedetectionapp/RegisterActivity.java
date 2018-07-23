package com.example.wang.livedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {

    private EditText mLoginText;
    private EditText mPasswordText;
    private EditText mNameText;
    private EditText mGenderText;
    private Button mResiterButton;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        mLoginText = findViewById(R.id.register_login_text);
        mPasswordText = findViewById(R.id.register_password_text);
        mNameText = findViewById(R.id.register_name_text);
        mGenderText = findViewById(R.id.register_gender_text);

        mResiterButton = findViewById(R.id.register_button);

        mResiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoginText.getText().length() > 0 && mPasswordText.getText().length() > 0 && mNameText.getText().length() > 0 && mGenderText.getText().length() > 0) {
                    MainActivity.mLogin = mLoginText.getText().toString();
                    MainActivity.mPassword = mPasswordText.getText().toString();
                    finish();
                }
            }
        });
    }
}
