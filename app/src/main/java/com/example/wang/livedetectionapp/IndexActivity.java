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

    private GridView mGridView;
    private RecyclerView mRecyclerView;

    private List<ImageInfo> mImageInfos;
    private List<MenuInfo> mMenuInfos;

    private GridViewAdapter mGridViewAdapter;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, IndexActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        initImageInfo();
        initMenuInfo();
        initView();

    }

    private void initImageInfo() {
        mImageInfos = new ArrayList<>();

        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.baseball);
        imageInfo.setName("棒球");
        mImageInfos.add(imageInfo);

        imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.book);
        imageInfo.setName("图书");
        mImageInfos.add(imageInfo);

        imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.coffee);
        imageInfo.setName("咖啡");
        mImageInfos.add(imageInfo);

        imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.diamond);
        imageInfo.setName("钻石");
        mImageInfos.add(imageInfo);

        imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.hamburger);
        imageInfo.setName("汉堡");
        mImageInfos.add(imageInfo);

        imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.instruments);
        imageInfo.setName("乐器");
        mImageInfos.add(imageInfo);

        imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.phone);
        imageInfo.setName("手机");
        mImageInfos.add(imageInfo);

        imageInfo = new ImageInfo();
        imageInfo.setDrawable(R.drawable.taxi);
        imageInfo.setName("出租");
        mImageInfos.add(imageInfo);

    }

    private void initMenuInfo() {
        mMenuInfos = new ArrayList<>();
        MenuAsyncTask menuAsyncTask = new MenuAsyncTask();
        menuAsyncTask.execute();
    }

    private void initView() {
        mGridView = findViewById(R.id.menu_grid_view);
        mGridViewAdapter = new GridViewAdapter(this, R.layout.item_menu_grid_view, mImageInfos);
        mGridView.setAdapter(mGridViewAdapter);
    }

    private class MenuAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return NetManager.linkHttp("http://www.imooc.com/api/shopping?type=11");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                mMenuInfos = NetManager.parseIndexGson(s);
            }
            mRecyclerView = findViewById(R.id.menu_recycler_view);
            mRecyclerViewAdapter = new RecyclerViewAdapter(IndexActivity.this, R.layout.item_menu_recycler_view, mMenuInfos);
            mRecyclerViewAdapter.setOnItemClickListen(new RecyclerViewAdapter.IOnItemClickListen() {
                @Override
                public void onItemClick(View view, int position) {
                    DetailActivity.startActivity(IndexActivity.this, "http://www.imooc.com/api/shopping?type=12");
                }
            });
            mRecyclerView.setLayoutManager(new LinearLayoutManager(IndexActivity.this));
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
        }
    }

}
