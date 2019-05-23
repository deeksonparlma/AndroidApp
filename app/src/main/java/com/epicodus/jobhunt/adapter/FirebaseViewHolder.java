package com.epicodus.jobhunt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.companyDetailActivity;
import com.epicodus.jobhunt.constants.Constants;
import com.epicodus.jobhunt.model.CompanyModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;
    public FirebaseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
public void bindCompanies(CompanyModel company){
    TextView nameTextView = mView.findViewById(R.id.companyNamee);
    nameTextView.setText(company.getmName());
    TextView publication = mView.findViewById(R.id.companyWebsitee);
    publication.setText("published :"+" "+company.getmPublicationDate());

}    @Override
    public void onClick(View v) {
        final ArrayList<CompanyModel> restaurants = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_JOB_SEARCHED).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    restaurants.add(snapshot.getValue(CompanyModel.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, companyDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("company", Parcels.wrap(restaurants));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
