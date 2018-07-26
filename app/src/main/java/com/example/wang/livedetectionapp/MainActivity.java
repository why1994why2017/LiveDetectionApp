package com.example.wang.livedetectionapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wang.livedetectionapp.Database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String mLogin;
    public static String mPassword;
    public static String mName;
    public static String mGender;

    private EditText mLoginTest;
    private EditText mPasswordTest;
    private Button mLoginButton;
    private TextView mRegisterButton;

    private MyDatabaseHelper dbHelper;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        dbHelper = new MyDatabaseHelper(this, "InfoStore.db", null, 2);
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
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("loginnum", mLogin);
                    values.put("passwords", mPassword);
                    values.put("name", mName);
                    values.put("gender", mGender);
                    values.put("templogin", mLoginTest.getText().toString());
                    db.insert("info", null, values);
                    values.clear();
                    String sql = "update info set id=1 where id<>1";
                    db.execSQL(sql);
                    //IndexFragment.startActivity(this);
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
