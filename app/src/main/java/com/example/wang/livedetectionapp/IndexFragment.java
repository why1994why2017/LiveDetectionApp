package com.example.wang.livedetectionapp;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wang.livedetectionapp.adapter.MenuRecyclerViewAdapter;
import com.example.wang.livedetectionapp.mode.MenuInfo;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment {

    private View mRootView;

    private RecyclerView mRecyclerView;
    private MenuRecyclerViewAdapter mMenuRecyclerViewAdapter;

    private List<MenuInfo> mMenuInfos;

    public static IndexFragment newInstance() {
        IndexFragment indexFragment = new IndexFragment();
        return indexFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {

            mRootView = inflater.inflate(R.layout.fragment_index, container, false);

            initMenuInfo();
            initView();
            initData();
        }
        return mRootView;
    }

    private void initMenuInfo() {
        mMenuInfos = new ArrayList<>();
    }

    private void initView() {
        mRecyclerView = mRootView.findViewById(R.id.menu_recycler_view);

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

        mMenuRecyclerViewAdapter = new MenuRecyclerViewAdapter(mRootView.getContext(), mMenuInfos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRootView.getContext()));
        mRecyclerView.setAdapter(mMenuRecyclerViewAdapter);
    }
}
