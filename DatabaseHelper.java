package com.example.fitme.fitme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.fitme.fitme.dbget.Exercise_Type;
import com.example.fitme.fitme.dbget.Get_Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2/16/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "FitMe.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.fitme.fitme/databases/"; //if cant open check the path
    public static final String USER_TABLE_NAME = "USERS";
    public static final String WORKOUT_TABLE_NAME = "WORKOUT";

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private int ListSize;

    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "PASSWORD";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "FIRST_NAME";
    public static final String COL_5 = "LAST_NAME";


    public static final String WCOL_1 = "USERNAME";
    public static final String WCOL_2 = "WID";
    public static final String WCOL_3 = "EXEC";
    public static final String WCOL_4 = "SETS";
    public static final String WCOL_5 = "REPS";
    public static final String WCOL_6 = "DAY";
    public static final String WCOL_7 = "MONTH";
    public static final String WCOL_8 = "YEAR";

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

    public List<Get_Exercise> getfirst_workout(){
        Get_Exercise exc = null;
        List<Get_Exercise> exercisesList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM WORKOUT " +
                "WHERE WID = '1' ", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            exc = new Get_Exercise(cursor.getString(0),cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
            exercisesList.add(exc);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return exercisesList;
    }

    public boolean insertData(String user, String pass, String email, String fName, String Lname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, user);
        contentValues.put(COL_2, pass);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, fName);
        contentValues.put(COL_5, Lname);
        //db.close();

        long result = db.insert(USER_TABLE_NAME, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;


    }

    public boolean insertDataDate(String user,  String wid, String ename, String set, String rep, String day, String month, String year)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WCOL_1, user);
        contentValues.put(WCOL_2, wid);
        contentValues.put(WCOL_3, ename);
        contentValues.put(WCOL_4, set);
        contentValues.put(WCOL_5, rep);
        contentValues.put(WCOL_6, day);
        contentValues.put(WCOL_7, month);
        contentValues.put(WCOL_8, year);


        long result = db.insert(WORKOUT_TABLE_NAME, null,contentValues);
        //db.close();
        if(result == -1)
            return false;
        else
            return true;




    }

    public List<Dates> getListDates(){
        Dates dates = null;
        List<Dates> datesList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT DAY,MONTH,YEAR FROM WORKOUT", null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            dates = new Dates(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            datesList.add(dates);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return datesList;
    }

}
