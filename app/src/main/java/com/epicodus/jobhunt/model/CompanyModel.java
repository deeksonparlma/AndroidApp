package com.epicodus.jobhunt.model;

import org.parceler.Parcel;

@Parcel
public class CompanyModel {
    private  String mDescription;
    private  String mLocation;
    private  String mIndustry;
    private String mTags;
    private  String mName;
    private String mPublicationDate;
    private  String mTwitter;
    private String mcompanySize;
    private String mRefs;

    public CompanyModel() {
    }

    public CompanyModel(String description, String location, String industry, String tags, String name, String pDate, String twitter, String cSize, String refs){
        mDescription =description;
        mLocation = location;
        mIndustry =industry;
        mTags = tags;
        mName = name;
        mPublicationDate =pDate;
        mTwitter = twitter;
        mcompanySize = cSize;
        mRefs =refs;
    }

    public String getmName() {
        return mName;
    }

    public String getmTwitter() {
        return mTwitter;
    }

    public String getmRefs() {
        return mRefs;
    }

    public String getmIndustry() {
        return mIndustry;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getMcompanySize() {
        return mcompanySize;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmPublicationDate() {
        return mPublicationDate;
    }

    public String getmTags() {
        return mTags;
    }
}
