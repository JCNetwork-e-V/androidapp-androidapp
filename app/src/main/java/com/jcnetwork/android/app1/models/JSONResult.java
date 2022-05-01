package com.jcnetwork.android.app1.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONResult implements Parcelable {

    // Variables
    @SerializedName("uname")
    private String mUserName;
    @SerializedName("programm")
    private List<ProgramPoint> mProgram;

    /**
     */
    public JSONResult(String mUserName,
                      List<ProgramPoint> mProgram) {
        this.mUserName = mUserName;
        this.mProgram = mProgram;
    }

    // Getter Methods
    public String getmUserName() {
        return mUserName;
    }
    public List<ProgramPoint>  getmProgram() {
        return mProgram;
    }

    /**
     * Parcelable part to pass along info
     * @param in a parcel
     */

    protected JSONResult(Parcel in) {
        mUserName = in.readString();
        mProgram = in.createTypedArrayList(ProgramPoint.CREATOR);
    }

    public static final Creator<JSONResult> CREATOR = new Creator<JSONResult>() {
        @Override
        public JSONResult createFromParcel(Parcel in) {
            return new JSONResult(in);
        }

        @Override
        public JSONResult[] newArray(int size) {
            return new JSONResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUserName);
        parcel.writeTypedList(mProgram);
    }
}
