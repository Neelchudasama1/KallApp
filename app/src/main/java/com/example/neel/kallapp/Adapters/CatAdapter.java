package com.example.neel.kallapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.neel.kallapp.Models.CatModel;
import com.example.neel.kallapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Neel on 06-Jul-16.
 */
public class CatAdapter extends BaseAdapter {

    ArrayList<CatModel> mArrayList = new ArrayList<>();
    Context context;
    boolean isONline;
    private static LayoutInflater inflater = null;


    //1 mean online
    public CatAdapter(Activity detailsActivity, ArrayList<CatModel> mArrayList, boolean isONline) {

        this.mArrayList = mArrayList;
        context = detailsActivity;
        this.isONline = isONline;

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

            convertView = inflater.inflate(R.layout.cust_categories, null);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);

        if (isONline) {
            Picasso.with(context).load(mArrayList.get(position).icon).into(iv);
        } else {
            iv.setImageBitmap(BitmapFactory.decodeByteArray(mArrayList.get(position).oflineIcon,
                    0, mArrayList.get(position).oflineIcon.length));
        }
        tvName.setText(mArrayList.get(position).name);
        tvId.setText(mArrayList.get(position).id);


        return convertView;
    }
}
