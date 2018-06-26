package com.eljebo.common.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by neeraj.narwal on 20/7/16.
 */
public class DBItem implements Parcelable{
    public long id;
    public String title;

    public DBItem(Parcel in) {
        id = in.readLong();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DBItem> CREATOR = new Creator<DBItem>() {
        @Override
        public DBItem createFromParcel(Parcel in) {
            return new DBItem(in);
        }

        @Override
        public DBItem[] newArray(int size) {
            return new DBItem[size];
        }
    };
}
