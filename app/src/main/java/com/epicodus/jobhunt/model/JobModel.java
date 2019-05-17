package com.epicodus.jobhunt.model;

public class JobModel {
String mCompany;
String mlocations;
String mrefs;
String mContents;
String mCategory;
String mtags;
String mLevels;

public JobModel(String company, String location, String ref, String contents, String category, String tags, String levels){
    this.mCategory = category;
    this.mCompany =company;
    this.mContents = contents;
    this.mrefs = ref;
    this.mlocations = location;
    this.mtags = tags;
    this.mLevels = levels;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmContents() {
        return mContents;
    }

    public String getmCompany() {
        return mCompany;
    }

    public String getMlocations() {
        return mlocations;
    }

    public String getMrefs() {
        return mrefs;
    }

    public String getmLevels() {
        return mLevels;
    }

    public String getMtags() {
        return mtags;
    }
}
