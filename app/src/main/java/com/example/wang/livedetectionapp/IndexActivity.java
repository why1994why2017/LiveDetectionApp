package com.example.wang.livedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.example.wang.livedetectionapp.adapter.GridViewAdapter;
import com.example.wang.livedetectionapp.adapter.RecyclerViewAdapter;
import com.example.wang.livedetectionapp.mode.ImageInfo;
import com.example.wang.livedetectionapp.mode.MenuInfo;
import com.example.wang.livedetectionapp.tool.NetManager;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    private List<MenuInfo> mMenuInfos;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, IndexActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        initMenuInfo();
        initView();
        initData();

    }

    private void initMenuInfo() {
        mMenuInfos = new ArrayList<>();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.menu_recycler_view);

    }

    private void initData() {
        mMenuInfos = new ArrayList<>();

        MenuInfo menuInfo1 = new MenuInfo(1, false);
        mMenuInfos.add(menuInfo1);

        MenuInfo menuInfo2 = new MenuInfo(2, false);
        mMenuInfos.add(menuInfo2);

        MenuInfo menuInfo3 = new MenuInfo(3, true);
        mMenuInfos.add(menuInfo3);

        MenuInfo menuInfo4 = new MenuInfo(4, true);
        mMenuInfos.add(menuInfo4);

        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mMenuInfos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
}
