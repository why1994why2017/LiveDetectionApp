package com.example.wang.livedetectionapp.mode;

public class MenuInfo {

    private int mTitle;
    private boolean mStatus;

    public MenuInfo(int title, boolean status) {
        mTitle = title;
        mStatus = status;
    }

    public int getTitle() {
        return mTitle;
    }

    public void setTitle(int title) {
        this.mTitle = title;
    }

    public boolean isStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        this.mStatus = status;
    }
}
