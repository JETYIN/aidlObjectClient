package com.work.service.aidlandroidservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/5/15.
 */

public class User implements Parcelable {
    public String userName;
    public String userPassword;

    public User(String userName, String userPassword) {
        this.userName=userName;
        this.userPassword=userPassword;
    }

    protected User(Parcel in) {
        userName = in.readString();
        userPassword = in.readString();
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userPassword);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
