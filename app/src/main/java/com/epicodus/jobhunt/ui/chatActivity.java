package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.jobhunt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class chatActivity extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.textView8) TextView mMessage;
@BindView(R.id.button8) Button mSend;
@BindView(R.id.editText7) EditText mTyping;
    @BindView(R.id.home)
    ImageView mHome;
    @BindView(R.id.jobs) ImageView mJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mSend.setOnClickListener(this);
        mJobs.setOnClickListener(this);
        mHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSend){
            String message =mTyping.getText().toString();
            mMessage.setText(message);
            mTyping.setText("");
        }
        else if (v == mJobs){
            Intent intent = new Intent(chatActivity.this, displaySavedJobs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mHome){
            Intent intent = new Intent(chatActivity.this,jobSearch.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
    }
}
