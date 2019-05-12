package com.epicodus.jobhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class chatActivity extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.textView8) TextView mMessage;
@BindView(R.id.button8) Button mSend;
@BindView(R.id.editText7) EditText mTyping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSend){
            String message =mTyping.getText().toString();
            mMessage.setText(message);
            mTyping.setText("");
        }
    }
}
