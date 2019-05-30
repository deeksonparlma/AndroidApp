package com.epicodus.jobhunt.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.model.CompanyModel;
import com.epicodus.jobhunt.util.ItemTouchHelperAdapter;
import com.epicodus.jobhunt.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FirebaseListAdapter extends FirebaseRecyclerAdapter<CompanyModel, FirebaseViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseListAdapter(FirebaseRecyclerOptions<CompanyModel> options, DatabaseReference ref, OnStartDragListener onStartDragListener, Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
    @Override
    protected void onBindViewHolder(final FirebaseViewHolder firebaseViewHolder, int position, CompanyModel company) {
        firebaseViewHolder.bindCompanies(company);
        firebaseViewHolder.mCompanyImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(firebaseViewHolder);
                }
                return false;
            }
        });
    }

    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_list_item_drag, parent, false);
        return new FirebaseViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        getRef(position).removeValue();
    }

}