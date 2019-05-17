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

public class jobListAdapter extends RecyclerView.Adapter<jobListAdapter.JobViewHolder> {

    private JobViewHolder jobViewHolder;
    private int i;
    private ArrayList<JobModel> mJobs = new ArrayList<>();
    private Context mContext;

    public jobListAdapter(Context context, ArrayList<JobModel> jobs) {
        mContext = context;
        mJobs = jobs;
    }

    @NonNull
    @Override
    public jobListAdapter.JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jobs_list, viewGroup, false);
        JobViewHolder viewHolder = new JobViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder( jobListAdapter.JobViewHolder jobHolder, int i) {

        this.jobViewHolder = jobViewHolder;
        this.i = i;
        jobHolder.bindJobs(mJobs.get(i));
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }
    public  class JobViewHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.companyLocation) TextView mCompanyLocation;
       @BindView(R.id.companyName) TextView mCompanyName;
       @BindView(R.id.companyLevel) TextView mCompanyLevel;
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);

        }
        public void bindJobs(JobModel jobs) {
            mCompanyName.setText(jobs.getmCompany());
            mCompanyLocation.setText(jobs.getMlocations());
            mCompanyLevel.setText(jobs.getmLevels());
        }
    }
}
