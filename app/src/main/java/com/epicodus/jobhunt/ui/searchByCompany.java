package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.adapter.companyListAdapter;
import com.epicodus.jobhunt.constants.Constants;
import com.epicodus.jobhunt.model.CompanyModel;
import com.epicodus.jobhunt.service.MuseService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class searchByCompany extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = searchByJob.class.getSimpleName();
@BindView(R.id.home) ImageView mHome;
@BindView(R.id.jobs) ImageView mJobs;
@BindView(R.id.chat) ImageView mChat;
@BindView(R.id.editText6) EditText mType;
@BindView(R.id.editText9) EditText mDesc;
@BindView(R.id.imageView13) ImageView mSearch;
@BindView(R.id.recyclerView2) RecyclerView mRecyclerView;
    public ArrayList<CompanyModel> mCompanyArray = new ArrayList<>();
    private companyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_company);
        ButterKnife.bind(this);
        mHome.setOnClickListener(this);
        mJobs.setOnClickListener(this);
        mChat.setOnClickListener(this);
        mSearch.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        ButterKnife.bind(this);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                String descending = mDesc.getText().toString();
                getCompany(query,descending);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v == mHome){
            Intent intent = new Intent(searchByCompany.this,jobSearch.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mJobs){
            Intent intent = new Intent(searchByCompany.this,displayAllJobs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mChat){
            Intent intent = new Intent(searchByCompany.this,chatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mSearch){
            String company = mType.getText().toString();
            String descending = mDesc.getText().toString();
            getCompany(company,descending);
            mType.setText("");
        }
    }
    private void getCompany(String company,String descending){
        final MuseService museService = new MuseService();
        museService.findCompanies(company, descending, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mCompanyArray = museService.companies(response);
                searchByCompany.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new companyListAdapter(getApplicationContext(),mCompanyArray);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(searchByCompany.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
//                try {
//                    String jsonData = response.body().string();
//                    mJobsArray.add(jsonData);
//                    Log.v(TAG, jsonData);
//
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}

