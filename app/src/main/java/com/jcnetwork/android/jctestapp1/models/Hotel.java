package com.jcnetwork.android.jctestapp1.models;

import com.google.gson.annotations.SerializedName;

public class Hotel {

    // Variables
    @SerializedName("name") // Dummy "Hotel Name"
    private final String mHotelName;
    @SerializedName("address") // Dummy "Hotelstr. 23, Heidelburg"
    private final String mHotelAddress;
    @SerializedName("phone") // Dummy "123-4456-7658"
    private final String mHotelPhone;
    @SerializedName("mail") // Dummy  "hotel@gmail.com"
    private final String mHotelMail;

    /**
     * Constructor for Hotel Object
     * @param name of hotel
     * @param address of hotel
     * @param phone of hotel
     * @param mail of hotel
     */
    public Hotel(String name,
                 String address,
                 String phone,
                 String mail) {
        mHotelName = name;
        mHotelAddress = address;
        mHotelPhone = phone;
        mHotelMail = mail;
    }

    // Getter Methods
    public String getmHotelName() {
        return mHotelName;
    }
    public String getmHotelAddress() {
        return mHotelAddress;
    }
    public String getmHotelPhone() {
        return mHotelPhone;
    }
    public String getmHotelMail() {
        return mHotelMail;
    }



}
