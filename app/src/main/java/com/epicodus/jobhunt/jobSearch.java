package com.epicodus.jobhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class jobSearch extends AppCompatActivity implements View.OnClickListener {
@BindView(R.id.button6) Button mCompany;
@BindView(R.id.button7) Button mJobName;
@BindView(R.id.jobs) ImageView mJobs;
@BindView(R.id.chat) ImageView mChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);
        ButterKnife.bind(this);

        mCompany.setOnClickListener(this);
        mJobName.setOnClickListener(this);
        mJobs.setOnClickListener(this);
        mChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mCompany){
            Intent intent = new Intent(jobSearch.this,searchByCompany.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mJobName){
            Intent intent = new Intent(jobSearch.this,searchByJob.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mJobs){
            Intent intent = new Intent(jobSearch.this,displayAllJobs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mChat){
            Intent intent = new Intent(jobSearch.this,chatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
    }
}
