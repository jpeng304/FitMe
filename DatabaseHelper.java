package com.example.fitme.fitme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fitme.fitme.dbget.Exercise_Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2/16/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "FitMe.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.fitme.fitme/databases/"; //if cant open check the path
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private int ListSize;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DB_NAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(mDatabase != null)
        {
            mDatabase.close();
        }
    }

    public List<Product> getListProductExName(){
        Product product = null;
        List<Product> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT BODY_TYPE FROM EXERCISES GROUP BY BODY_TYPE", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            product = new Product(cursor.getString(0));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }

    public List<Exercise_Type> getExc_type(){
        Exercise_Type type = null;
        List<Exercise_Type> typeList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT EXC_TYPE FROM EXERCISES GROUP BY EXC_TYPE", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            type = new Exercise_Type(cursor.getString(0));
            typeList.add(type);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return typeList;
    }

    public List<Exercise_Type> getExc_name(){
        Exercise_Type type = null;
        List<Exercise_Type> typeList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT EXC_NAME FROM EXERCISES", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            type = new Exercise_Type(cursor.getString(0));
            typeList.add(type);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return typeList;
    }

    public List<Exercise_Type> getExc_name_arms_body(){
        Exercise_Type type = null;
        List<Exercise_Type> typeList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT EXC_NAME FROM EXERCISES " +
            "WHERE BODY_TYPE = 'Arms' AND EXC_TYPE = 'Body'", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            type = new Exercise_Type(cursor.getString(0));
            typeList.add(type);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return typeList;
    }


}
