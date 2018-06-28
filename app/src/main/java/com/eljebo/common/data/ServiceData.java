package com.eljebo.common.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOXSL\vinay.goyal on 16/6/18.
 */

public class ServiceData implements Parcelable {

    public final static Parcelable.Creator<ServiceData> CREATOR = new Creator<ServiceData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceData createFromParcel(Parcel in) {
            return new ServiceData(in);
        }

        public ServiceData[] newArray(int size) {
            return (new ServiceData[size]);
        }

    };
    @SerializedName("service_id")
    @Expose
    public Integer id;
    @SerializedName("service_name")
    @Expose
    public String title;
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
    @SerializedName("sub_service")
    @Expose
    public List<SubService> subServices = new ArrayList<>();
    public boolean isExpand;
    protected ServiceData(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.typeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stateId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.isExpand = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.createdById = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.subServices, (SubService.class.getClassLoader()));
    }

    public ServiceData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(typeId);
        dest.writeValue(stateId);
        dest.writeValue(isExpand);
        dest.writeValue(createdOn);
        dest.writeValue(createdById);
        dest.writeList(subServices);
    }

    public int describeContents() {
        return 0;
    }

}