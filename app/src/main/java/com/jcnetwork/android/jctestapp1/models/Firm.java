package com.jcnetwork.android.jctestapp1.models;

public class Firm {

    // Variables with some empty string defaults
    private String name;
    private String description;
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

    public Firm(String name, String description, String homePageLink) {
        this.name = name;
        this.description = description;
        this.homePageLink = homePageLink;
    }

    public Firm(String name, String description, String homePageLink, String xingLink, String linkedInLink) {
        this.name = name;
        this.description = description;
        this.homePageLink = homePageLink;
        this.xingLink = xingLink;
        this.linkedInLink = linkedInLink;
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

}
