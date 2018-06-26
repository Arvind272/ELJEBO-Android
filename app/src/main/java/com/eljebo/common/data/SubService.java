package com.eljebo.common.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TOXSL\vinay.goyal on 16/6/18.
 */

public class SubService implements Parcelable {

    public final static Parcelable.Creator<SubService> CREATOR = new Creator<SubService>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SubService createFromParcel(Parcel in) {
            return new SubService(in);
        }

        public SubService[] newArray(int size) {
            return (new SubService[size]);
        }

    };
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("service_id")
    @Expose
    public Integer serviceId;
    @SerializedName("type_id")
    @Expose
    public Integer typeId;
    @SerializedName("state_id")
    @Expose
    public Integer stateId;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("created_by_id")
    @Expose
    public Integer createdById;
    public boolean isChecked;
    public String price;

    protected SubService(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.typeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stateId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isChecked = ((Boolean) in.readValue((Boolean.class.getClassLoader())));

        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.createdById = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public SubService() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(price);
        dest.writeValue(serviceId);
        dest.writeValue(typeId);
        dest.writeValue(stateId);
        dest.writeValue(isChecked);
        dest.writeValue(createdOn);
        dest.writeValue(createdById);
    }

    public int describeContents() {
        return 0;
    }

}