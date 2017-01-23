package com.example.neel.kallapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.neel.kallapp.Models.CompanyModel;
import com.example.neel.kallapp.R;

import java.util.ArrayList;

/**
 * Created by Neel on 06-Jul-16.
 */
public class CompanyAdapter extends BaseAdapter {

    ArrayList<CompanyModel> mArrayList = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater = null;
    String id;


    //1 mean online
    public CompanyAdapter(Activity detailsActivity, ArrayList<CompanyModel> mArrayList, String id) {

        this.mArrayList = mArrayList;
        context = detailsActivity;
        this.id = id;

    }


    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.cust_company, null);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvComId = (TextView) convertView.findViewById(R.id.tvCatId);
        TextView tvCatId = (TextView) convertView.findViewById(R.id.tvId);


        tvName.setText(mArrayList.get(position).name);
        tvComId.setText(mArrayList.get(position).company_id);
        tvCatId.setText(mArrayList.get(position).categoryid);


        return convertView;
    }
}
