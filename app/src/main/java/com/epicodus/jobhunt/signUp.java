package com.epicodus.jobhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class signUp extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.button3) Button mLandScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mLandScreen.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mLandScreen){
            Intent intent = new Intent(signUp.this,HomeActivity.class);
            startActivity(intent);
        }
    }
}
