package com.epicodus.jobhunt.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.jobhunt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class signUp extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = signUp.class.getSimpleName();
@BindView(R.id.button3) Button mSignUp;
@BindView(R.id.editText) EditText mUsername;
@BindView(R.id.editText3) EditText mEmail;
@BindView(R.id.editText2) EditText mPassword;
@BindView(R.id.editText4) EditText mConfirmPassword;
@BindView(R.id.editText5) EditText mTelephone;
    private FirebaseAuth mAuth;
    private ProgressDialog mAuthProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mSignUp.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();
    }

    //progress bar//
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
    @Override
    public void onClick(View v) {
        final String username =mUsername.getText().toString().trim();
        final String Email = mEmail.getText().toString();
        final String password =mPassword.getText().toString();
        final String confirmPass =mConfirmPassword.getText().toString();
        final String phone =mTelephone.getText().toString();

        if(v == mSignUp) {
            if (username.isEmpty()) {
                mUsername.setError("invalid Username");
            } else if (!(Patterns.EMAIL_ADDRESS.matcher(Email).matches())) {
                mEmail.setError("invalid email");
            } else if (mPassword.getText().toString().length() < 8) {
                mPassword.setError("password is less than 8 characters");
            } else if (!(confirmPass.matches(password))) {
                mConfirmPassword.setError("password mismatch");
            } else if (phone.length() != 10) {
                mTelephone.setError("invalid number");
            } else if (!username.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(Email).matches() && mPassword.getText().toString().length() >= 8 && confirmPass.matches(password) && phone.length() == 10) {
                mAuthProgressDialog.show();
                mAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String usernamee = mUsername.getText().toString();
                                    mAuthProgressDialog.dismiss();
                                    Toast.makeText(signUp.this, "Sign up successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(signUp.this, HomeActivity.class);
                                    intent.putExtra("username", usernamee);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivityForResult(intent, 0);
                                    finish();
                                } else {
                                    mAuthProgressDialog.dismiss();
                                    Toast.makeText(signUp.this, Objects.requireNonNull(task.getException()).getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
        //code//

    }
}
