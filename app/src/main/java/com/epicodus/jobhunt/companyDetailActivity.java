package com.epicodus.jobhunt;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.jobhunt.adapter.pageAdapter;
import com.epicodus.jobhunt.model.CompanyModel;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class companyDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private pageAdapter adapterViewPager;
    ArrayList<CompanyModel> mCompany = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        ButterKnife.bind(this);
        mCompany = Parcels.unwrap(getIntent().getParcelableExtra("company"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new pageAdapter(getSupportFragmentManager(), mCompany);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
