package com.epicodus.jobhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class jobSearch extends AppCompatActivity implements View.OnClickListener {
@BindView(R.id.button6) Button mCompany;
@BindView(R.id.button7) Button mJobName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);
        ButterKnife.bind(this);
        mCompany.setOnClickListener(this);
        mJobName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mCompany){
            Intent intent = new Intent(jobSearch.this,searchByCompany.class);
            startActivity(intent);
        }
        else if (v == mJobName){
            Intent intent = new Intent(jobSearch.this,searchByJob.class);
            startActivity(intent);
        }

    }
}
