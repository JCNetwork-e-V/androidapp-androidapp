package com.jcnetwork.android.jctestapp1.models;

import com.google.gson.annotations.SerializedName;

/**
 * Set up for organizer object
 */

public class Organizer {

    // Variables
    @SerializedName("name") // Dummy "Bodensee Consult?"
    private final String mOrgName;
    @SerializedName("description") // Dummy "Beschreibung des Vereins"
    private final String mOrgDescription;
    @SerializedName("mail") // Dummy "verein@gmail.com"
    private final String mOrgMail;
    @SerializedName("website") // Dummy  "verein.com"
    private final String mOrgWebsite;
    @SerializedName("image") // Dummy  "http://image.png"
    private final String mOrgImage;

    /**
     * Constructor for organizer
     * @param name of club
     * @param description of club
     * @param mail of club
     * @param website of club
     * @param image of club
     */
    public Organizer(String name,
                 String description,
                 String mail,
                 String website,
                 String image) {
        mOrgName = name;
        mOrgDescription = description;
        mOrgMail = mail;
        mOrgWebsite = website;
        mOrgImage = image;
    }

    // Getter Methods
    public String getmOrgName() {
        return mOrgName;
    }
    public String getmOrgDescription() {
        return mOrgDescription;
    }
    public String getmOrgMail() {
        return mOrgMail;
    }
    public String getmOrgWebsite() {
        return mOrgWebsite;
    }
    public String getmOrgImage() {
        return mOrgImage;
    }


}
