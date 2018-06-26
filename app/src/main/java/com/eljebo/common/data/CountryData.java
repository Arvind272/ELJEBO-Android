package com.eljebo.common.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TOXSL\vinay.goyal on 16/6/18.
 */

public class CountryData implements Parcelable {

    public final static Parcelable.Creator<CountryData> CREATOR = new Creator<CountryData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CountryData createFromParcel(Parcel in) {
            return new CountryData(in);
        }

        public CountryData[] newArray(int size) {
            return (new CountryData[size]);
        }

    };
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("country_id")
    @Expose
    public Integer countryId;
    @SerializedName("type_id")
    @Expose
    public Integer typeId;
    @SerializedName("city_state_id")
    @Expose
    public Integer cityStateId;
    @SerializedName("state_id")
    @Expose
    public Integer stateId;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("created_by_id")
    @Expose
    public Integer createdById;

    protected CountryData(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.countryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.typeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cityStateId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stateId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.createdById = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public CountryData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(countryId);
        dest.writeValue(typeId);
        dest.writeValue(cityStateId);
        dest.writeValue(stateId);
        dest.writeValue(createdOn);
        dest.writeValue(createdById);
    }

    public int describeContents() {
        return 0;
    }

}


