package com.example.neel.kallapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Neel on 06-Jul-16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_cat.db";


    public SQLiteHelper(Context mContext) {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        CategoiesTable.CREATE_CATEGORRIS_TABLE = "create table if not exists " + CategoiesTable.CATEGORIES_TABLE + "(" + CategoiesTable.cat_id + " varchar2(3)," + CategoiesTable.cat_name + " varchar2(30) ," + CategoiesTable.image + " BLOB)";
        Log.e("QQQ", "Table create : " + CategoiesTable.CREATE_CATEGORRIS_TABLE);
        db.execSQL(CategoiesTable.CREATE_CATEGORRIS_TABLE);

        CompaniesTable.CREATE_COMPANY_TABLE = "create table if not exists " + CompaniesTable.COMPANY_TABLE + "(" + CompaniesTable.categoryid + " varchar2(3)," + CompaniesTable.name + " varchar2(30) ," + CompaniesTable.company_id + " varchar2(4))";
        Log.e("QQQ", "Table create : " + CompaniesTable.CREATE_COMPANY_TABLE);
        db.execSQL(CompaniesTable.CREATE_COMPANY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CategoiesTable.CATEGORIES_TABLE + "");
        Log.e("QQQ", "Table drop");
        this.onCreate(db);

    }
}
