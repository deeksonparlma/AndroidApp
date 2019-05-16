package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.jobhunt.R;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.Username) TextView mUsername;
@BindView(R.id.button5) Button mMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mUsername.setText("Welcome " +username + " "+ "to our Job Hunt platform,check the guide below");
        mMain.setOnClickListener(this);
        MobileAds.initialize(this, "ca-app-pub-6291199528234939~3659960798");
//        AdRequest request = new AdRequest.Builder()
//                .addTestDevice("33BE2250B43518CCDA7DE426D04EE231")  // An example device ID
//                .build();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeActivity.this,jobSearch.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(intent, 0);
    }
}
