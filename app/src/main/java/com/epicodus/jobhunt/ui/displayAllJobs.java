package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.adapter.customArrayAdapter;
import com.epicodus.jobhunt.service.MuseService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class displayAllJobs extends AppCompatActivity implements View.OnClickListener{
//    public static final String TAG = displayAllJobs.class.getSimpleName();
    GridView gridView;

    String[] companies = new String[]{
            "Safaricom ltd" , "Moringa School","Google"
    };
    String[] vacancies = new String[]{
      "Software Developer" , "Technical Mentor","Data Scientist"
    };
    @BindView(R.id.textView10) GridView mjobsInCompanies;
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
//        customArrayAdapter adapter = new customArrayAdapter(this, android.R.layout.simple_list_item_1, companies,vacancies);
//        mjobsInCompanies.setAdapter(adapter);
//        gridView = (GridView) findViewById(R.id.textView10);
        mjobsInCompanies.setAdapter(new customArrayAdapter(this,companies,vacancies));
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
