package com.example.neel.kallapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.neel.kallapp.Models.CatModel;

import java.util.ArrayList;

/**
 * Created by Neel on 06-Jul-16.
 */
public class CategoiesTable extends SQLiteHelper {

    private Context mContext;

    public static String cat_id = "cat_id";
    public static String cat_name = "cat_name";
    public static String image = "image";
    public static String CATEGORIES_TABLE = "table_cat";
    public static String CREATE_CATEGORRIS_TABLE;

    public CategoiesTable(Context context) {
        super(context);

        mContext = context;
    }

    public void deleteRecord() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + this.CATEGORIES_TABLE + "");
    }

    public void storeCategorisData(String cat_id, String cat_name, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(this.cat_id, cat_id);
        values.put(this.cat_name, cat_name);
        values.put(this.image, image);

        db.insert(CategoiesTable.CATEGORIES_TABLE, null, values);
        db.close();
    }

    public ArrayList<CatModel> getOfflineCategories() {
        ArrayList<CatModel> arrayList = new ArrayList<>();
        String query = "SELECT  * FROM " + CATEGORIES_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CatModel model;
        if (cursor.moveToFirst()) {
            do {
                model = new CatModel();
                model.id = (cursor.getString(cursor.getColumnIndex("" + cat_id + "")));
                model.name = (cursor.getString(cursor.getColumnIndex("" + cat_name + "")));
                model.oflineIcon = cursor.getBlob(2);


                arrayList.add(model);
            } while (cursor.moveToNext());
        }

        Log.e("QQQ", "ofline size : " + arrayList.size());

        return arrayList;
    }

}
