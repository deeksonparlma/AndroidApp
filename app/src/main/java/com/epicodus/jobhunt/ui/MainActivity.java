package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.jobhunt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.buttonn) Button msignUpButton;
    @BindView(R.id.button2) Button mloginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        msignUpButton.setOnClickListener(this);
        mloginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == msignUpButton) {
            Intent intent = new Intent(MainActivity.this, signUp.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mloginButton){
            Intent intent = new Intent(MainActivity.this,Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
    }
}
