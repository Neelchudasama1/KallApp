package com.example.neel.kallapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.neel.kallapp.Adapters.CompanyAdapter;
import com.example.neel.kallapp.Models.CompanyModel;

import java.util.ArrayList;

public class CatDetailsActivity extends BaseActivity {

    ListView mCompanyListView;
    ArrayList<CompanyModel> mCompanyList = new ArrayList<>();
    CompanyAdapter mCompanyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // db.onUpgrade(db.getWritableDatabase(), 1, 2);


        Intent i = getIntent();
        String id = i.getStringExtra("ID");

        mCompanyListView = (ListView) findViewById(R.id.catList);

        //   new apiCatDetailList(id).execute();


    }

/*
    public class apiCatDetailList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;
        String id;

        public apiCatDetailList(String id) {
            super();
            this.id = id;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            Log.e("WWW", "start api asynctask");
            try {
                ParsedResponse p = Soap.apiGetCategoriesDetails();
                error = p.error;
                if (!error) {
                    mCompanyList = (ArrayList<CompanyModel>) p.o;

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
            Log.e("WWW", "complate api task");

            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            if (!error) {
                Log.e(getLocalClassName(), " Array Loist size" + mCompanyList.size());
                mCompanyListAdapter = new CompanyAdapter(CatDetailsActivity.this, mCompanyList,id);
                mCompanyListView.setAdapter(mCompanyListAdapter);


            }
        }
    }
*/

}
