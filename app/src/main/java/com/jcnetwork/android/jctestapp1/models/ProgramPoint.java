package com.jcnetwork.android.jctestapp1.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Class for constructing a program point object from JSON response
 */
@Entity(tableName = "program")
public class ProgramPoint implements Parcelable {

    /*
    Variables to fill out with serializedname giving the actual key names in the JSON
     */
    @SerializedName("indx") // Int! e.g. 0 // This is the name in the json i.e. the "key" so spelling errors are copied as is
    @PrimaryKey
    private final int mIndex;

    @SerializedName("id") // e.g. "1"
    private final String mEventId;

    @SerializedName("days_id") // e.g. "10"
    private final String mDaysId;

    @SerializedName("title") // e.g. "Check-In"
    private final String mTitle;

    @SerializedName("address") // e.g. "Wuerzburg+Hauptbahnhof,97070,Wuerzburg"
    private final String mAddress;

    @SerializedName("beginn") // e.g. "2019-11-21 16:00:00"
    private String mBegin;

    @SerializedName("end") // e.g. "2019-11-21 16:00:00"
    private String mEnd;

    @SerializedName("img") // e.g. "Check-In_W"
    private final String mImage;

    @SerializedName("undertitle") // e.g. "Empfangshalle Hauptbahnhof" oder aber "Workshopslot"
    private final String mPlace;

    @SerializedName("description") // e.g. "Wir freuen...blah blah..."
    private final String mDescription;

    @SerializedName("color") // e.g. "white"
    private final String mColor;


    /**
     * Constructor
     * @param mIndex
     * @param mEventId
     * @param mDaysId
     * @param mTitle
     * @param mAddress
     * @param mPlace
     * @param mColor
     * @param mDescription
     * @param mBegin
     * @param mEnd
     * @param mImage
     */
    public ProgramPoint(int mIndex,
                 String mEventId,
                 String mDaysId,
                 String mTitle,
                 String mAddress,
                 String mPlace,
                 String mColor,
                 String mDescription,
                 String mBegin,
                 String mEnd,
                 String mImage) {
        this.mIndex = mIndex;
        this.mEventId = mEventId;
        this.mDaysId = mDaysId;
        this.mTitle = mTitle;
        this.mAddress = mAddress;
        this.mBegin = mBegin;
        this.mEnd = mEnd;
        this.mImage = mImage;
        this.mPlace = mPlace;
        this.mDescription = mDescription;
        this.mColor = mColor;
    }

    /**
     * Getter methods
     */
    public int getIndex() {return this.mIndex;}
    public String getTitle() {
        return this.mTitle;
    }
    public String getEventId() {
        return this.mEventId;
    }
    public String getDaysId() {
        return this.mDaysId;
    }
    public String getAddress() {
        return this.mAddress;
    }
    public String getBegin() {
        return this.mBegin;
    }
    public String getEnd() {
        return this.mEnd;
    }
    public String getImage() {
        return this.mImage;
    }
    public String getPlace() {
        return this.mPlace;
    }
    public String getColor() {
        return this.mColor;
    }
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * Setter methods
     */
    public void setBegin(String begin) {
         this.mBegin = begin;
    }
    public void setEnd(String end) {
         this.mEnd = end;
    }


    /**
     * Parcelable part
     * @param in a parcel
     */
    protected ProgramPoint(Parcel in) {
        mIndex = in.readInt();
        mEventId = in.readString();
        mDaysId = in.readString();
        mTitle = in.readString();
        mAddress = in.readString();
        mBegin = in.readString();
        mEnd = in.readString();
        mImage = in.readString();
        mPlace = in.readString();
        mDescription = in.readString();
        mColor = in.readString();
    }

    public static final Creator<ProgramPoint> CREATOR = new Creator<ProgramPoint>() {
        @Override
        public ProgramPoint createFromParcel(Parcel in) {
            return new ProgramPoint(in);
        }

        @Override
        public ProgramPoint[] newArray(int size) {
            return new ProgramPoint[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mIndex);
        parcel.writeString(mEventId);
        parcel.writeString(mDaysId);
        parcel.writeString(mTitle);
        parcel.writeString(mAddress);
        parcel.writeString(mBegin);
        parcel.writeString(mEnd);
        parcel.writeString(mImage);
        parcel.writeString(mPlace);
        parcel.writeString(mDescription);
        parcel.writeString(mColor);
    }
}
