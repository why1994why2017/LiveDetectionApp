package com.example.wang.livedetectionapp.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonInfo implements Parcelable {

    private String loginName;
    private String mPassword;

    private String personName;
    private String gender;

    public PersonInfo(){}

    protected PersonInfo(Parcel in) {
        loginName = in.readString();
        mPassword = in.readString();
        personName = in.readString();
        gender = in.readString();
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
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loginName);
        dest.writeString(mPassword);
        dest.writeString(personName);
        dest.writeString(gender);
    }
}
