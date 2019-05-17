package com.epicodus.jobhunt.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.model.JobModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class jobListAdapter extends RecyclerView.Adapter<jobListAdapter.JobViewHolder> {

    private int i;
    private ArrayList<JobModel> mJobs;
    private Context mContext;

    public jobListAdapter(Context context, ArrayList<JobModel> jobs) {
        mContext = context;
        mJobs = jobs;
    }

    @Override
    public jobListAdapter.JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jobs_list, viewGroup, false);
        JobViewHolder viewHolder = new JobViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder( jobListAdapter.JobViewHolder jobHolder, int i) {

        jobHolder.bindJobs(mJobs.get(i));
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public  class JobViewHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.companyLocation) TextView mCompanyLocation;
       @BindView(R.id.companyName) TextView mCompanyName;
       @BindView(R.id.companyWebsite) TextView mCompanyWebsite;
        public JobViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

        }
        public void bindJobs(JobModel jobs) {
            mCompanyName.setText(" Company:"+" "+jobs.getmCompany());
            mCompanyLocation.setText(" location :"+" "+jobs.getMlocations());
            mCompanyWebsite.setText(" Category:"+" "+jobs.getmCategory());
        }
    }
}
