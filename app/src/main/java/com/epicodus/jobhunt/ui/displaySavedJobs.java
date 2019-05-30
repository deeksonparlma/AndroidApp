package com.epicodus.jobhunt.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.adapter.FirebaseListAdapter;
import com.epicodus.jobhunt.adapter.FirebaseViewHolder;
import com.epicodus.jobhunt.constants.Constants;
import com.epicodus.jobhunt.model.CompanyModel;
import com.epicodus.jobhunt.util.OnStartDragListener;
import com.epicodus.jobhunt.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class displaySavedJobs extends AppCompatActivity implements View.OnClickListener, OnStartDragListener {
//    public static final String TAG = displaySavedJobs.class.getSimpleName();
//    GridView gridView;
//
//    String[] companies = new String[]{
//            "Safaricom ltd" , "Moringa School","Google"
//    };
//    String[] vacancies = new String[]{
//      "Software Developer" , "Technical Mentor","Data Scientist"
//    };
//    @BindView(R.id.textView10) GridView mjobsInCompanies;
    @BindView(R.id.home) ImageView mHome;
    @BindView(R.id.jobs) ImageView mJobs;
    @BindView(R.id.chat) ImageView mChat;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private DatabaseReference mRef;
    private FirebaseListAdapter mFirebaseAdapter;
    private  ItemTouchHelper mItemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_jobs);
        ButterKnife.bind(this);
        mHome.setOnClickListener(this);
        mJobs.setOnClickListener(this);
        mChat.setOnClickListener(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_JOB_SEARCHED).child(uid);
        setUpFirebaseAdapter();
//        customArrayAdapter adapter = new customArrayAdapter(this, android.R.layout.simple_list_item_1, companies,vacancies);
//        mjobsInCompanies.setAdapter(adapter);
//        gridView = (GridView) findViewById(R.id.textView10);
//        mjobsInCompanies.setAdapter(new customArrayAdapter(this,companies,vacancies));
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<CompanyModel> options =
                new FirebaseRecyclerOptions.Builder<CompanyModel>()
                        .setQuery(mRef, CompanyModel.class)
                        .build();

        mFirebaseAdapter = new FirebaseListAdapter(options, mRef, this, this);
//            @Override
//            protected void onBindViewHolder(@NonNull FirebaseViewHolder firebaseRestaurantViewHolder, int position, @NonNull CompanyModel company) {
//                firebaseRestaurantViewHolder.bindCompanies(company);
//            }
//
//            @NonNull
//            @Override
//            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_list_item_drag, parent, false);
//                return new FirebaseViewHolder(view);
//            }
//        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mHome){
            Intent intent = new Intent(displaySavedJobs.this,jobSearch.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if(v == mJobs){
            Intent intent = new Intent(displaySavedJobs.this, displaySavedJobs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
        else if (v == mChat){
            Intent intent = new Intent(displaySavedJobs.this,chatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }
}
