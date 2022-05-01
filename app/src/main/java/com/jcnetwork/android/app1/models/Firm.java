package com.jcnetwork.android.app1.models;

public class Firm {

    // Variables with some empty string defaults
    private String name;
    private String description;
    private String logoImg;
    // Homepage
    private String homePageLink = "";
    // Professional profiles
    private String xingLink = "";
    private String linkedInLink = "";
    // User favorite
    private Boolean isFavorite = Boolean.FALSE;

    /**
     * Default Constructor
     * @param name of the firm
     * @param description of the firm
     */
    public Firm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Firm(String name, String description, String homePageLink, String logoImg) {
        this.name = name;
        this.description = description;
        this.homePageLink = homePageLink;
        this.logoImg = logoImg;
    }

    public Firm(String name, String description, String homePageLink, String logoImg, String xingLink, String linkedInLink) {
        this.name = name;
        this.description = description;
        this.homePageLink = homePageLink;
        this.xingLink = xingLink;
        this.linkedInLink = linkedInLink;
        this.logoImg = logoImg;
    }

    // Getter Methods
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getHomePageLink() {
        return homePageLink;
    }
    public String getXingLink() { return xingLink; }
    public String getLinkedInLink() { return linkedInLink; }
    public Boolean getIsFavorite() { return isFavorite; }
    public String getImageLink() { return logoImg; }


    // Setter Methods
    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
    public void setXingLink(String xingLink) {
        this.xingLink = xingLink;
    }
    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }
    public void setHomePageLink(String homePageLink) {
        this.homePageLink = homePageLink;
    }
    public void setImageLink(String imageLink) { this.logoImg = imageLink; }


}
