package com.example.neel.kallapp.Utils;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.example.neel.kallapp.Models.CompanyModel;
import com.example.neel.kallapp.Models.LazyLodingModel;
import com.example.neel.kallapp.Models.dot_net;
import com.example.neel.kallapp.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by qtm-purvesh on 28/1/15.
 **/
/*

1. http://kallapp.madword-media.co.uk/categories.php
        2. http://kallapp.madword-media.co.uk/company.php?category_id=11
        3. http://kallapp.madword-media.co.uk/department.php?ctid=11&cmpid=5
*/

@SuppressWarnings("ALL")
public class Soap {

    private static String TAG = "SOAP";
        private static String BASE_URL_API = "http://kallapp.madword-media.co.uk/";
    private static final String CHARSET = "UTF-8";
    private static final String RESULT_OK = "authenticated";


    public static String getSoapResponsePost(String requestURL, Uri.Builder postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(/*BASE_URL_API + */requestURL);
            Log.d(TAG, url.toString());
            Log.d(TAG, postDataParams.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(15000);

            // uri builder
            String query = postDataParams.build().getEncodedQuery();

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, CHARSET));
            writer.write(query);
            writer.flush();
            writer.close();
            httpURLConnection.connect();

            int resCode = httpURLConnection.getResponseCode();

            if (resCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response = response + line;
                }
            } else {
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, response);
        return response;

    }

    public static String getSoapResponseByPost(String requestURL) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            Log.d(TAG, url.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(15000);

            httpURLConnection.connect();

            int resCode = httpURLConnection.getResponseCode();

            if (resCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response = response + line;
                }
            } else {
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, response);
        return response;

    }

    public static ParsedResponse apiGetCategories() throws JSONException {
        ParsedResponse p = new ParsedResponse();


        if (Utils.isNetworkAvailable(Utils.context)) {


            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("username", "mob")
                    .appendQueryParameter("password", "mob123");

            String res = getSoapResponsePost("http://192.168.28.79/OrderManagerApi/OrderManagerAPI/SetCredentials?", builder);


            if (!TextUtils.isEmpty(res)) {

                JSONObject objData = new JSONObject(res);
                Gson gson = new Gson();
                dot_net model = new dot_net();
                model = gson.fromJson(objData.toString(), dot_net.class);

                p.error = false;
                p.o = model;


            } else {
                p.error = true;
                p.o = Utils.context.getString(R.string.err_prblm_loading_data);
            }

        } else

        {
            p.error = true;
            p.o = Utils.context.getString(R.string.err_no_internet);
        }
        return p;
    }

    public static ParsedResponse apiGetCategoriesDetails2(String id) throws JSONException {
        ParsedResponse p = new ParsedResponse();

        if (Utils.isNetworkAvailable(Utils.context)) {


            String res = getSoapResponseByPost("company.php?category_id=" + id + "");
            if (!TextUtils.isEmpty(res)) {
                JSONObject objData = new JSONObject(res);
                JSONArray arrData = objData.getJSONArray("companies");
                ArrayList<CompanyModel> arrList = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < arrData.length(); i++) {
                    JSONObject c = arrData.getJSONObject(i);
                    CompanyModel model = new CompanyModel();
                    model = gson.fromJson(c.toString(), CompanyModel.class);
                    arrList.add(model);
                }
                p.error = false;
                p.o = arrList;


            } else {
                p.error = true;
                p.o = Utils.context.getString(R.string.err_prblm_loading_data);
            }

        } else

        {
            p.error = true;
            p.o = Utils.context.getString(R.string.err_no_internet);
        }
        return p;
    }

    public static ParsedResponse apiLazyLoding(String pageNumber) throws JSONException {
        ParsedResponse p = new ParsedResponse();


        if (Utils.isNetworkAvailable(Utils.context)) {


            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("user_id", "2333")
                    .appendQueryParameter("latitude", "23")
                    .appendQueryParameter("longitude", "73")
                    .appendQueryParameter("page_id", pageNumber);


            String res = getSoapResponsePost("http://yyppee.com/dev/api/user/businessUserList/", builder);


            if (!TextUtils.isEmpty(res)) {

                JSONObject objData = new JSONObject(res);
                JSONArray arrData = objData.getJSONArray("success");
                ArrayList<LazyLodingModel> arrList = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < arrData.length(); i++) {
                    JSONObject c = arrData.getJSONObject(i);
                    LazyLodingModel model = new LazyLodingModel();
                    model = gson.fromJson(c.toString(), LazyLodingModel.class);
                    arrList.add(model);
                }
                p.error = false;
                p.o = arrList;


            } else {
                p.error = true;
                p.o = Utils.context.getString(R.string.err_prblm_loading_data);
            }

        } else

        {
            p.error = true;
            p.o = Utils.context.getString(R.string.err_no_internet);
        }
        return p;
    }


}
