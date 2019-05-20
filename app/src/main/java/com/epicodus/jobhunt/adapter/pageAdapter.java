package com.epicodus.jobhunt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.jobhunt.model.CompanyModel;
import com.epicodus.jobhunt.model.companyDetailFragment;

import java.util.ArrayList;

public class pageAdapter extends FragmentPagerAdapter {
    private ArrayList<CompanyModel> mCompany = new ArrayList<>();
    public pageAdapter(FragmentManager fm,ArrayList<CompanyModel> company) {
        super(fm);
        mCompany = company;
    }

    @Override
    public Fragment getItem(int i) {
        return companyDetailFragment.newInstance(mCompany.get(i));
    }

    @Override
    public int getCount() {
        return mCompany.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mCompany.get(position).getmPublicationDate();
    }
}
