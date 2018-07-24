package com.example.wang.livedetectionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyselfActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mInformate;
    private Button mAccounts;
    private Button mKnowledge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_page);

        initView();
    }

    private void initView() {
        mInformate = findViewById(R.id.self_information_button);
        mAccounts = findViewById(R.id.self_task_button);
        mKnowledge = findViewById(R.id.self_knowledge_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.self_information_button:
                InformationActivity.startActivity(this);
                break;

        }
    }
}
