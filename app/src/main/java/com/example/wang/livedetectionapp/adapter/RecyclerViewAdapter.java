package com.example.wang.livedetectionapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wang.livedetectionapp.R;
import com.example.wang.livedetectionapp.mode.MenuInfo;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<MenuInfo> mMenuInfos;
    private int mResource;

    private IOnItemClickListen mOnItemClickListen;

    public RecyclerViewAdapter (Context context, int resource, List<MenuInfo> menuInfos) {
        mContext = context;
        mResource = resource;
        mMenuInfos = menuInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        MenuInfo menuInfo = mMenuInfos.get(position);

        String url = menuInfo.getImage();
        Glide.with(mContext)
                .load(url)
                .into(holder.mFootImage);
        holder.mFootName.setText(menuInfo.getName());
        holder.mFootDescription.setText(menuInfo.getDescription());
        holder.mFootPrice.setText(String.format(mContext.getString(R.string.price), menuInfo.getPrice()));
        holder.mFootCount.setText(String.format(mContext.getString(R.string.count), menuInfo.getCount()));

        if (mOnItemClickListen != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListen.onItemClick(v, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mMenuInfos.size();
    }

    public interface IOnItemClickListen {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListen(IOnItemClickListen onItemClickListen) {
        mOnItemClickListen = onItemClickListen;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mFootImage;
        TextView mFootName;
        TextView mFootDescription;
        TextView mFootPrice;
        TextView mFootCount;

        public ViewHolder(View itemView) {
            super(itemView);

            mFootImage = itemView.findViewById(R.id.foot_image);
            mFootName = itemView.findViewById(R.id.foot_name);
            mFootDescription = itemView.findViewById(R.id.foot_description);
            mFootPrice = itemView.findViewById(R.id.foot_price);
            mFootCount = itemView.findViewById(R.id.foot_count);
        }
    }
}
