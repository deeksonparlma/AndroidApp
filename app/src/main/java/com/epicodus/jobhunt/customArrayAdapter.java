package com.epicodus.jobhunt;

import android.content.Context;

public class customArrayAdapter extends android.widget.ArrayAdapter {
    private Context mContext;
    private String[] mcompanies;
    private String[] mvacancies;

    public customArrayAdapter(Context mContext, int resource, String[] mcompanies, String[] mvacancies) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mcompanies = mcompanies;
        this.mvacancies = mvacancies;
    }
    @Override
    public Object getItem(int position) {
        String companies = mcompanies[position];
        String vacancies= mvacancies[position];
        return String.format("%s \nhas %s \nvacancies", companies, vacancies);
    }

    @Override
    public int getCount() {
        return mcompanies.length;
    }
}

