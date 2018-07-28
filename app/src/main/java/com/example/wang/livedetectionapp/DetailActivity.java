package com.example.wang.livedetectionapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wang.livedetectionapp.common.AppUtil;
import com.example.wang.livedetectionapp.common.BaseActivity;
import com.example.wang.livedetectionapp.tool.NetManager;

public class DetailActivity extends BaseActivity {

    private final static int MESSAGE_DETAIL = 1;

    private String strURL;
    private DetailInfo mDetailInfo;

    //头布局
    private Button mBackButton;
    private TextView mNameText;

    private ImageView mImageView;
    private TextView mOriginalPriceText;
    private TextView mTPriceText;
    private TextView mPriceText;
    private TextView mDescriptionText;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_DETAIL:
                    mDetailInfo = (DetailInfo) msg.obj;
                    updateData();
                    break;
                default:
                    break;
            }
        }
    };

    public static void startActivity(Context context, String strURL) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("URL", strURL);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        strURL = bundle.getString("URL");

        initView();
        initData();

    }

    private void initView() {
        mBackButton = findViewById(R.id.common_button);
        mNameText = findViewById(R.id.common_text_view);
        mImageView = findViewById(R.id.detail_image);
        mOriginalPriceText = findViewById(R.id.detail_original_price);
        mTPriceText = findViewById(R.id.detail_t_price);
        mPriceText = findViewById(R.id.detail_price);
        mDescriptionText = findViewById(R.id.detail_description);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.finishCurrentActivity();
            }
        });
    }

    private void initData() {
        new DetailThread().start();
    }

    private void updateData(){
        if (mDetailInfo == null) {
            return;
        }

        mNameText.setText(mDetailInfo.getName());

        Glide.with(this)
                .load(mDetailInfo.getImg())
                .into(mImageView);

        mDescriptionText.setText(mDetailInfo.getDescription());
    }

    private class DetailThread extends Thread {

        @Override
        public void run() {
            super.run();
            mDetailInfo = NetManager.parseDetailGson(NetManager.linkHttp(strURL));
            Message message = new Message();
            message.obj = mDetailInfo;
            message.what = MESSAGE_DETAIL;
            handler.sendMessage(message);
        }
    }
}
