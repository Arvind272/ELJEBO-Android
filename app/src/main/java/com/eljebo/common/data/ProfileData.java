package com.eljebo.common.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

/**
 * Created by TOXSL\harleen.dutt on 18/6/18.
 */

public class ProfileData implements Parcelable {

    public static final Creator<ProfileData> CREATOR = new Creator<ProfileData>() {
        @Override
        public ProfileData createFromParcel(Parcel in) {
            return new ProfileData(in);
        }

        @Override
        public ProfileData[] newArray(int size) {
            return new ProfileData[size];
        }
    };
    public String first_name;
    public String last_name;
    public String email;
    public String username;
    public String password;
    public String contact_no;
    public String address;
    public String address_two;
    public String country;
    public String city;
    public String availability_time_from;
    public String availability_time_to;
    public String zipcode;
    public int gender;
    public String city_state;
    public JSONArray security_question;
    public JSONArray sub_services;
    public JSONArray multiImage;
    public String education_level;
    public String certification;
    public String cardHolderName;
    public String card_number;
    public String cvv;
    public String expiry_year;
    public String expiry_month;
    public int paymentType;
    public int roleId;

    public int countryIds;
    public int stateIds;
    public int cityIds;


    public ProfileData(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        email = in.readString();
        username = in.readString();
        password = in.readString();
        contact_no = in.readString();
        address = in.readString();
        address_two = in.readString();
        country = in.readString();
        city = in.readString();
        availability_time_from = in.readString();
        availability_time_to = in.readString();
        zipcode = in.readString();
        gender = in.readInt();
        city_state = in.readString();
        education_level = in.readString();
        certification = in.readString();
        roleId = in.readInt();

        countryIds = in.readInt();
        stateIds = in.readInt();
        cityIds = in.readInt();
    }

    public ProfileData() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(contact_no);
        parcel.writeString(address);
        parcel.writeString(address_two);
        parcel.writeString(country);
        parcel.writeString(city);
        parcel.writeString(availability_time_from);
        parcel.writeString(availability_time_to);
        parcel.writeString(zipcode);
        parcel.writeString(certification);
        parcel.writeInt(gender);
        parcel.writeInt(roleId);
        parcel.writeString(city_state);
        parcel.writeString(education_level);

        parcel.writeInt(countryIds);
        parcel.writeInt(stateIds);
        parcel.writeInt(cityIds);
    }
}
