package com.epicodus.jobhunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class customArrayAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mcompanies;
    private String[] mvacancies;

    public customArrayAdapter(Context mContext, String[] mcompanies, String[] mvacancies) {
        this.mContext = mContext;
        this.mcompanies = mcompanies;
        this.mvacancies = mvacancies;
    }

    @Override
    public int getCount() {
        return mcompanies.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            // get layout from xml file
            gridView = inflater.inflate(R.layout.customlayout, null);
            // pull views
            TextView letterView = (TextView) gridView
                    .findViewById(R.id.grid_item_letter);

            TextView numberTv = gridView.findViewById(R.id.grid_item_number);
            // set values into views
            letterView.setText("Company :"+" "+mcompanies[position]);  // using dummy data for now
            numberTv.setText("position" + " "+ mvacancies[position]);
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
