package com.epicodus.jobhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MobileAds.initialize(this, "ca-app-pub-6291199528234939~3659960798");
//        AdRequest request = new AdRequest.Builder()
//                .addTestDevice("33BE2250B43518CCDA7DE426D04EE231")  // An example device ID
//                .build();
    }
}
