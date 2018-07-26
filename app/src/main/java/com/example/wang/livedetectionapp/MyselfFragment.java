package com.example.wang.livedetectionapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MyselfFragment extends Fragment implements View.OnClickListener {

    private View mRootView;

    private LinearLayout mInformate;
    private LinearLayout mAccounts;
    private LinearLayout mKnowledge;

    public static MyselfFragment newInstance() {
        MyselfFragment myselfFragment = new MyselfFragment();
        return myselfFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_self_page, container, false);

            initView();
        }
        return mRootView;
    }

    private void initView() {
        mInformate = mRootView.findViewById(R.id.self_information_text);
        mInformate.setOnClickListener(this);

        mAccounts = mRootView.findViewById(R.id.self_task_text);
        mAccounts.setOnClickListener(this);

        mKnowledge = mRootView.findViewById(R.id.self_knowledge_text);
        mKnowledge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.self_information_text:
                InformationActivity.startActivity(mRootView.getContext());
                break;
            default:
                break;
        }
    }
}
