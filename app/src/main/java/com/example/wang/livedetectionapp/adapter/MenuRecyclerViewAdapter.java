package com.example.wang.livedetectionapp.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wang.livedetectionapp.R;
import com.example.wang.livedetectionapp.mode.MenuInfo;

import java.util.List;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<MenuInfo> mMenuInfos;

    public MenuRecyclerViewAdapter(Context context, List<MenuInfo> menuInfos) {
        mContext = context;
        mMenuInfos = menuInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu_recycler_view, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        MenuInfo menuInfo = mMenuInfos.get(position);

        holder.mMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (holder.mDetailLayout.getVisibility() == View.GONE) {
                   holder.mDetailLayout.setVisibility(View.VISIBLE);
               } else {
                   holder.mDetailLayout.setVisibility(View.GONE);
               }
            }
        });
        String title = String.format(mContext.getResources().getString(R.string.task_title), menuInfo.getTitle());
        holder.mTitleText.setText(title);

        holder.mStatusText.setText(menuInfo.isStatus() ? mContext.getResources().getString(R.string.status_completed) : mContext.getResources().getString(R.string.status_false));

    }

    @Override
    public int getItemCount() {
        return mMenuInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleText;
        TextView mStatusText;
        ImageView mMenuImageView;
        LinearLayout mDetailLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.menu_title_text);
            mStatusText = itemView.findViewById(R.id.menu_status_text);
            mMenuImageView = itemView.findViewById(R.id.menu_image_view);
            mDetailLayout = itemView.findViewById(R.id.menu_detail_layout);
        }
    }
}
