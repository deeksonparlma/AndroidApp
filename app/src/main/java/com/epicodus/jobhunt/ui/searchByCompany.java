package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.adapter.companyListAdapter;
import com.epicodus.jobhunt.model.CompanyModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class searchByCompany extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = searchByJob.class.getSimpleName();
@BindView(R.id.home) ImageView mHome;
@BindView(R.id.jobs) ImageView mJobs;
@BindView(R.id.chat) ImageView mChat;
@BindView(R.id.recyclerView2) RecyclerView mRecyclerView;
    public ArrayList<CompanyModel> mJobsArray = new ArrayList<>();
    private companyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_company);
        ButterKnife.bind(this);
        mHome.setOnClickListener(this);
        mJobs.setOnClickListener(this);
        mChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mHome){
            Intent intent = new Intent(searchByCompany.this,jobSearch.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mJobs){
            Intent intent = new Intent(searchByCompany.this,displayAllJobs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mChat){
            Intent intent = new Intent(searchByCompany.this,chatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
    }
}

