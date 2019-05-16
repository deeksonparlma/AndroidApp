package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.service.MuseService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class searchByJob extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = searchByJob.class.getSimpleName();
    @BindView(R.id.home)
    ImageView mHome;
    @BindView(R.id.jobs)
    ImageView mJobs;
    @BindView(R.id.chat)
    ImageView mChat;
    @BindView(R.id.editText6)
    EditText mType;
    @BindView(R.id.imageView13) ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_job);
        ButterKnife.bind(this);

        mHome.setOnClickListener(this);
        mJobs.setOnClickListener(this);
        mChat.setOnClickListener(this);
        mSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mHome) {
            Intent intent = new Intent(searchByJob.this, jobSearch.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        } else if (v == mJobs) {
            Intent intent = new Intent(searchByJob.this, displayAllJobs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        } else if (v == mChat) {
            Intent intent = new Intent(searchByJob.this, chatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mSearch){
            String jobs = mType.getText().toString();
            getJobs(jobs);
        }
    }
    private void getJobs(String jobs){
        final MuseService museService = new MuseService();
        museService.findJobs(jobs, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
