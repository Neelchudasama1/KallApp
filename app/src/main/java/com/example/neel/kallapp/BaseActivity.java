package com.example.neel.kallapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.neel.kallapp.Utils.Utils;

/**
 * Created by Neel on 06-Jul-16.
 */
public class BaseActivity extends AppCompatActivity {

    public ProgressDialog mProgressDialog;
    //public SessionManager mSessionManager;
    private static final int REQUEST_CODE_LOCATION_SETTINGS = 0x1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.context = this;
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
    }


}
