package com.example.wang.livedetectionapp.adapter;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang.livedetectionapp.R;
import com.example.wang.livedetectionapp.mode.ImageInfo;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter<ImageInfo> {

    private Context mContext;
    private List<ImageInfo> mImageInfos;
    private int mResource;

    public GridViewAdapter(@NonNull Context context, int resource, List<ImageInfo> imageInfos) {
        super(context, resource, imageInfos);
        mContext = context;
        mResource = resource;
        mImageInfos = imageInfos;
    }


    @Override
    public int getCount() {
        return mImageInfos.size();
    }

    @Override
    public ImageInfo getItem(int position) {
        return mImageInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.menu_image_view);
            viewHolder.textView = convertView.findViewById(R.id.menu_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageInfo imageInfo = getItem(position);
        if (imageInfo != null) {
            viewHolder.imageView.setImageResource(imageInfo.getDrawable());
            viewHolder.textView.setText(imageInfo.getName());
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
