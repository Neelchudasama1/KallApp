package com.example.neel.kallapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.neel.kallapp.Adapters.LazyLoddingAdapter;
import com.example.neel.kallapp.Models.LazyLodingModel;
import com.example.neel.kallapp.Utils.ParsedResponse;
import com.example.neel.kallapp.Utils.Soap;

import org.json.JSONException;

import java.util.ArrayList;

public class LazyLoadingActivity extends BaseActivity {

    ListView mListView;
    ArrayList<LazyLodingModel> arrList = new ArrayList<>();
    // ArrayList<LazyLodingModel> arrList2 = new ArrayList<>();
    LazyLoddingAdapter adapter;
    int mPageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_loading);

        mListView = (ListView) findViewById(R.id.listView);


        new apiLineList().execute();


        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int first = mListView.getFirstVisiblePosition();
                int count = mListView.getChildCount();

                Log.e("AAA", "First  : " + first); // 14
                Log.e("AAA", "Count   : " + count); // 6
                Log.e("AAA", "Adapter count : " + adapter.getCount());
                Log.e("AAA", "==================");


                if (scrollState == SCROLL_STATE_IDLE && (first + count == adapter.getCount())) {
                    mPageNumber = mPageNumber + 1;
                    new apiLineList().execute();
                }


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }


    public class apiLineList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            Log.e("WWW", "start api asynctask");
            Log.e("AAA", "PAge number : " + mPageNumber);
            try {
                ParsedResponse p = Soap.apiLazyLoding(String.valueOf(mPageNumber));
                error = p.error;
                if (!error) {

                    arrList = new ArrayList<>();
                    arrList = (ArrayList<LazyLodingModel>) p.o;


                } else {
                    msg = (String) p.o;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                error = true;
                msg = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            if (!error) {
                Log.e("AAA", " Array List size" + arrList.size());

                // arrList.addAll(arrList2);
                if (mPageNumber == 1) {
                    adapter = new LazyLoddingAdapter(LazyLoadingActivity.this, arrList);
                    mListView.setAdapter(adapter);
                }
                else {
                    adapter.addAll(arrList);
                    adapter.notifyDataSetChanged();
                }
                //new storeDatabase().execute();
            }
        }
    }

}
