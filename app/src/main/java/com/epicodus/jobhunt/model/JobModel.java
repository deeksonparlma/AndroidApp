package com.epicodus.jobhunt.model;

public class JobModel {
String mCompany;
String mlocations;
String mrefs;
String mContents;
String mCategory;

public JobModel(String company,String location,String ref,String contents,String category){
    this.mCategory = category;
    this.mCompany =company;
    this.mContents = contents;
    this.mrefs = ref;
    this.mlocations = location;
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
}
