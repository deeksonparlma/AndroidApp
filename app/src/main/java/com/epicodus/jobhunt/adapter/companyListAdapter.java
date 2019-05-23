package com.epicodus.jobhunt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.companyDetailActivity;
import com.epicodus.jobhunt.model.CompanyModel;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class companyListAdapter extends RecyclerView.Adapter<companyListAdapter.CompanyViewHolder> {
    private ArrayList<CompanyModel> mCompany;
    private Context mContext;

    public companyListAdapter(Context applicationContext, ArrayList<CompanyModel> mCompanyArray) {
        mContext = applicationContext;
        mCompany = mCompanyArray;
    }

    @Override
    public companyListAdapter.CompanyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_list, viewGroup, false);
        companyListAdapter.CompanyViewHolder viewHolder = new CompanyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(companyListAdapter.CompanyViewHolder companyViewHolder, int i) {
        companyViewHolder.bindCompanies(mCompany.get(i));
    }

    @Override
    public int getItemCount() {
       return mCompany.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
//        @BindView(R.id.companyLocationn) TextView mCompanyLocation;
        @BindView(R.id.companyNamee) TextView mCompanyName;
        @BindView(R.id.companyWebsitee) TextView mmCompany;


        public CompanyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mContext =view.getContext();
            itemView.setOnClickListener(this);

        }
        public void bindCompanies(CompanyModel company){
            mCompanyName.setText(" Company:"+" "+company.getmName());
//            mCompanyLocation.setText(" location(s) :"+" "+company.getmLocation());
            mmCompany.setText("published :"+" "+company.getmPublicationDate());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, companyDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("company", Parcels.wrap(mCompany));
            mContext.startActivity(intent);
        }
    }
}
