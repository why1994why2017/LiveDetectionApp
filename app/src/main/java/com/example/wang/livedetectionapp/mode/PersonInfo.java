package com.example.wang.livedetectionapp.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonInfo implements Parcelable {

    private String mLoginName;
    private String mPassword;
    private String mPersonName;
    private String mGender;

    public PersonInfo(){}

    protected PersonInfo(Parcel in) {
        mLoginName = in.readString();
        mPassword = in.readString();
        mPersonName = in.readString();
        mGender = in.readString();
    }

    public static final Parcelable.Creator<PersonInfo> CREATOR = new Parcelable.Creator<PersonInfo>() {
        @Override
        public PersonInfo createFromParcel(Parcel in) {
            return new PersonInfo(in);
        }

        @Override
        public PersonInfo[] newArray(int size) {
            return new PersonInfo[size];
        }
    };

    public String getLoginName() {
        return mLoginName;
    }

    public void setLoginName(String mLoginName) {
        mLoginName = mLoginName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPersonName() {
        return mPersonName;
    }

    public void setPersonName(String personName) {
        mPersonName = personName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLoginName);
        dest.writeString(mPassword);
        dest.writeString(mPersonName);
        dest.writeString(mGender);
    }
}
