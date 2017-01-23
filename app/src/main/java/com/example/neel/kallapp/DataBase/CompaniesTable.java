package com.example.neel.kallapp.DataBase;

import android.content.Context;

/**
 * Created by Neel on 07-Jul-16.
 */
public class CompaniesTable extends SQLiteHelper {

    public static String categoryid = "categoryid";
    public static String name = "name";
    public static String company_id = "company_id";

    public static String CREATE_COMPANY_TABLE = "create_companytable";
    public static String COMPANY_TABLE = "companytable";


    public CompaniesTable(Context mContext) {
        super(mContext);
    }
}
