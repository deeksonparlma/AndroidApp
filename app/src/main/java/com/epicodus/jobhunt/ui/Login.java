package com.epicodus.jobhunt.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.jobhunt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.editText10) EditText mEmail;
@BindView(R.id.editText11) EditText mPassword;
@BindView(R.id.btn) Button mLogin;
@BindView(R.id.textView14) TextView mError;
@BindView(R.id.imageView15) ImageView mHazard;
    private ProgressDialog mAuthProgressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mError.setVisibility(View.INVISIBLE);
        mHazard.setVisibility(View.INVISIBLE);
        mLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();
    }
    //progress dialog//
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loggin in...");
        mAuthProgressDialog.setMessage("Checking Credentials...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        final String email = mEmail.getText().toString();
        final String password =mPassword.getText().toString();
        if(v == mLogin){
            if(mEmail.getText().toString().trim().isEmpty()){
                mEmail.setError("invalid username");
            }
            else if( mPassword.getText().toString().trim().isEmpty()){
                mPassword.setError("invalid password");
            }
            else if( mPassword.getText().toString().length() < 8){
                mPassword.setError("password's less than 8 characters");
            }

//            else if(mLogin.length()<0){
//                Intent intent = new  Intent(this,jobSearch.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivityForResult(intent, 0);
//                finish();
//            }
            else if(!email.isEmpty() && !password.isEmpty() ){
                mAuthProgressDialog.show();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mAuthProgressDialog.dismiss();
                                    Intent intent = new  Intent(Login.this,jobSearch.class);
                                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                     startActivityForResult(intent, 0);
                                     finish();
                                } else {
                                    mAuthProgressDialog.dismiss();
                                    mError.setVisibility(View.VISIBLE);
                                    mHazard.setVisibility(View.VISIBLE);
                                    Toast.makeText(Login.this, "Login failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}
