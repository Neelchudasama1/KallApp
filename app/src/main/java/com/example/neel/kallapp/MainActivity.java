package com.example.neel.kallapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.neel.kallapp.Adapters.CatAdapter;
import com.example.neel.kallapp.DataBase.CategoiesTable;
import com.example.neel.kallapp.DataBase.SQLiteHelper;
import com.example.neel.kallapp.Models.CatModel;
import com.example.neel.kallapp.Models.dot_net;
import com.example.neel.kallapp.Utils.ParsedResponse;
import com.example.neel.kallapp.Utils.Soap;
import com.example.neel.kallapp.Utils.Utils;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    public ListView mCatListView;
    ProgressDialog progressDialog;
    ArrayList<CatModel> mCategoriesList = new ArrayList<>();
    CatAdapter mCatAdapter;
    CategoiesTable categoiesTable = new CategoiesTable(this);
    SQLiteHelper db = new CategoiesTable(this);
    dot_net dotnetModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // db.onUpgrade(db.getWritableDatabase(), 1, 2);


        mCatListView = (ListView) findViewById(R.id.catList);

        mCatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), CatDetailsActivity.class);
                i.putExtra("ID", mCategoriesList.get(position).id);
                startActivity(i);
            }
        });

        if (Utils.isNetworkAvailable(this)) {
            categoiesTable.deleteRecord();
            new apiLineList().execute();
        } else {
            getOflineValue();

        }


    }

    public void getOflineValue() {
        ArrayList<CatModel> offlineCategoriesList = categoiesTable.getOfflineCategories();
        mCatAdapter = new CatAdapter(this, offlineCategoriesList, false);
        mCatListView.setAdapter(mCatAdapter);
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
            try {
                ParsedResponse p = Soap.apiGetCategories();
                error = p.error;
                if (!error) {
                    dotnetModel = (dot_net) p.o;


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
            Log.e("EEE" , ""+dotnetModel.result);
            Log.e("EEE" , ""+dotnetModel.status);

            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            if (!error) {
                Log.e(getLocalClassName(), " Array Loist size" + mCategoriesList.size());
                mCatAdapter = new CatAdapter(MainActivity.this, mCategoriesList, true);
                mCatListView.setAdapter(mCatAdapter);


                new storeDatabase().execute();
            }
        }
    }

    public class storeDatabase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Log.e("WWW", "start databse task");

            for (int i = 0; i < mCategoriesList.size(); i++) {

                byte[] logoImage = getLogoImage(mCategoriesList.get(i).icon);
                categoiesTable.storeCategorisData(mCategoriesList.get(i).id, mCategoriesList.get(i).name, logoImage);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.e("WWW", "databse task over");
        }
    }

    private byte[] getLogoImage(String url) {
        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayBuffer baf = new ByteArrayBuffer(500);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }

            return baf.toByteArray();
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
    }
}
