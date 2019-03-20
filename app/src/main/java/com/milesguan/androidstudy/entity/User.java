package com.milesguan.androidstudy.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * 测试用model
 *
 */
public class User implements Parcelable {

    private String name;

    private boolean isAdult;

    private School school;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public boolean isMe(){
        return "Miles".equals(name);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeByte(this.isAdult ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.school, flags);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.isAdult = in.readByte() != 0;
        this.school = in.readParcelable(School.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
