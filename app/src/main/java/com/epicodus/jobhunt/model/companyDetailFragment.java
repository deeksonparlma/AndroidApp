package com.epicodus.jobhunt.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.jobhunt.R;
import com.epicodus.jobhunt.constants.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;


public class companyDetailFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    @BindView(R.id.ts) TextView mName;
    @BindView(R.id.website) TextView web;
    @BindView(R.id.textView12) TextView location;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.publicationDate) TextView published;
    @BindView(R.id.twitter) TextView twitter;
    @BindView(R.id.tags) TextView tags;
    @BindView(R.id.save) Button mSave;
    @BindView(R.id.imageView14) ImageView mImageLabel;
    private CompanyModel company;
    private String mSource;

    public companyDetailFragment() {
    }
    public static companyDetailFragment newInstance(CompanyModel company) {
        companyDetailFragment fragment = new companyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("company", Parcels.wrap(company));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        company = Parcels.unwrap(getArguments().getParcelable("company"));
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.menu_photo, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
            default:
                break;
        }
        return false;
    }
    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageLabel.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }
    //saving image to database//
    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_JOB_SEARCHED)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.setValue(imageEncoded);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_company_detail, container, false);
        ButterKnife.bind(this,view);
        mName.setText(company.getmName());
        web.setText("website : "+company.getmRefs());
        location.setText("Location (s) :"+" "+company.getmLocation());
        description.setText(company.getmDescription());
        published.setText("published on"+" "+company.getmPublicationDate());
        twitter.setText("twitter :@"+company.getmTwitter());
        tags.setText(company.getmTags());
        web.setOnClickListener(this);
        mSave.setOnClickListener(this);
        return  view;

    }

    @Override
    public void onClick(View v) {
        if(v == web){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(company.getmRefs()));
            startActivity(webIntent);
    }
        if (v == mSave) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference jobRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_JOB_SEARCHED)
                    .child(uid);
            DatabaseReference pushRef = jobRef.push();
            String pushId = pushRef.getKey();
            company.setPushId(pushId);
            pushRef.setValue(company);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            mSave.setVisibility(View.INVISIBLE);
        }
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
