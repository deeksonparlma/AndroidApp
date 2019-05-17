package com.epicodus.jobhunt.model;

public class CompanyModel {
    private  String mDescription;
    private String mIndustry;
    private String mCompany;
    private String mDatePosted;
    private String mSize;
    private String mRefs;

    public CompanyModel(String description,String industry,String company,String vDate,String size,String Refs){
        mCompany = company;
        mDatePosted =vDate;
        mDescription = description;
        mIndustry = industry;
        mRefs = Refs;
        mSize = size;
    }

    public String getmCompany() {
        return mCompany;
    }

    public String getmDatePosted() {
        return mDatePosted;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmIndustry() {
        return mIndustry;
    }

    public String getmRefs() {
        return mRefs;
    }

    public String getmSize() {
        return mSize;
    }
}
