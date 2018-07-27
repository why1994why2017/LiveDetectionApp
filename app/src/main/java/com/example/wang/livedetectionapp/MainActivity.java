package com.example.wang.livedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wang.livedetectionapp.Database.MyDatabaseHelper;
import com.example.wang.livedetectionapp.common.AppUtil;
import com.example.wang.livedetectionapp.common.BaseActivity;
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
            case R.id.login_button:

                if (mLoginTest.getText().toString() == null || mPasswordTest.getText().toString() == null) {
                    break;
                }

                /*if (mLoginTest.getText().toString().equals(temploginnum) && mPasswordTest.getText().toString().equals(temppasswords)) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    String sql1 = "insert into info(templogin) values(" + mLogin + ")";
                    db.execSQL(sql1);
                    IndexUIActivity.startActivity(this);
                    AppUtil.finishCurrentActivity();
                }*/

                break;
            case R.id.main_register_button:
                RegisterActivity.startActivity(this);
                AppUtil.finishCurrentActivity();
                break;
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
