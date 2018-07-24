package com.example.wang.livedetectionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyselfActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mInformate;
    private LinearLayout mAccounts;
    private LinearLayout mKnowledge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_page);

        initView();
    }

    private void initView() {
        mInformate = findViewById(R.id.self_information_text);
        mInformate.setOnClickListener(this);

        mAccounts = findViewById(R.id.self_task_text);
        mAccounts.setOnClickListener(this);

        mKnowledge = findViewById(R.id.self_knowledge_text);
        mKnowledge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.self_information_text:
                InformationActivity.startActivity(this);
                break;

        }
    }
}
