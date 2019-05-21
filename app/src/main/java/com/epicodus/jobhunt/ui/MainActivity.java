package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.epicodus.jobhunt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.buttonn) Button msignUpButton;
    @BindView(R.id.button2) Button mloginButton;
    @BindView(R.id.imageView6) ImageView mInstagram;
    @BindView(R.id.imageView7) ImageView mTwitter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        msignUpButton.setOnClickListener(this);
        mloginButton.setOnClickListener(this);
        mInstagram.setOnClickListener(this);
        mTwitter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == msignUpButton) {
            Intent intent = new Intent(MainActivity.this, signUp.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            finish();
        }
        else if(v == mloginButton){
            Intent intent = new Intent(MainActivity.this,Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mInstagram){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/__deekson__/"));
            startActivity(webIntent);
        }
        else if(v == mTwitter){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/deekson_"));
            startActivity(webIntent);
        }
    }
}
