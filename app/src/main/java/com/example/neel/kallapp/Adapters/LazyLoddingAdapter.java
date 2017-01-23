package com.example.neel.kallapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.neel.kallapp.Models.LazyLodingModel;
import com.example.neel.kallapp.R;

import java.util.ArrayList;

/**
 * Created by Neel on 06-Jul-16.
 */
public class LazyLoddingAdapter extends BaseAdapter {

    ArrayList<LazyLodingModel> mArrayList = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater = null;


    //1 mean online
    public LazyLoddingAdapter(Activity detailsActivity, ArrayList<LazyLodingModel> mArrayLis) {

        this.mArrayList.clear();
        this.mArrayList.addAll(mArrayLis);
        //this.mArrayList = mArrayLis;
        context = detailsActivity;
        this.notifyDataSetChanged();

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
        return position;
    }

    public void addAll(ArrayList<LazyLodingModel> mArrayLis) {
        this.mArrayList.addAll(mArrayLis);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.textview, null);

        TextView str = (TextView) convertView.findViewById(R.id.textView);


        str.setText(mArrayList.get(position).business_name);


        return convertView;
    }
}
