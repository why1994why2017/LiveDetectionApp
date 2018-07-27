package com.example.wang.livedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.wang.livedetectionapp.common.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexUIActivity extends BaseActivity {

    private List<String> mTitles;
    private List<Fragment> mFragments;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, IndexUIActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        initView();
        initData();

    }

    private void initView() {
        mTabLayout = findViewById(R.id.index_tab_layout);
        mViewPager = findViewById(R.id.index_view_page);
    }

    private void initData() {
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.table_name)));
        mFragments = new ArrayList<>();
        mFragments.add(IndexFragment.newInstance());
        mFragments.add(MyselfFragment.newInstance());

        setFragments(mTitles, mFragments);

    }

    private void setFragments(List<String> titles, List<Fragment> fragments) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<String> mTitles;
        private List<Fragment> mFragments;

        private ViewPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
            super(fm);
            mTitles = titles;
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }

}
