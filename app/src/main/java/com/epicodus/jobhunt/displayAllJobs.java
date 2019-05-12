package com.epicodus.jobhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class displayAllJobs extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.home) ImageView mHome;
    @BindView(R.id.jobs) ImageView mJobs;
    @BindView(R.id.chat) ImageView mChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_jobs);
        ButterKnife.bind(this);
        mHome.setOnClickListener(this);
        mJobs.setOnClickListener(this);
        mChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mHome){
            Intent intent = new Intent(displayAllJobs.this,jobSearch.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mJobs){
            Intent intent = new Intent(displayAllJobs.this,displayAllJobs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mChat){
            Intent intent = new Intent(displayAllJobs.this,chatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
    }
}
