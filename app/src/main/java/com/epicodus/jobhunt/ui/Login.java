package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.jobhunt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.editText10) EditText mUsername;
@BindView(R.id.editText11) EditText mPassword;
@BindView(R.id.btn) Button mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mLogin){
            if(mUsername.getText().toString().trim().isEmpty()){
                mUsername.setError("invalid username");
            }
            else if( mPassword.getText().toString().trim().isEmpty()){
                mPassword.setError("invalid password");
            }
            else if( mPassword.getText().toString().length() < 8){
                mPassword.setError("password's less than 8 characters");
            }

            else{
                Intent intent = new  Intent(this,jobSearch.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
            }
        }
    }
}
