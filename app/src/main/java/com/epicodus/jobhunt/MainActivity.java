package com.epicodus.jobhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.button) Button msignupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        msignupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == msignupButton) {
            Intent intent = new Intent(MainActivity.this, logIn.class);
            startActivity(intent);
        }
    }
}
