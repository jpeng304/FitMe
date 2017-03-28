package com.example.fitme.fitme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


public class Calendar extends ActionBarActivity
{
    private List<Dates> mDatesList;
    private DatabaseHelper mDBHelper;
    private String storemonth;
    private String storeday;
    private String storeyear;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);

        mDBHelper = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DB_NAME);

        Log.d("HELLO", "FILE:" + database);
        if(database.exists()){
            mDBHelper.getReadableDatabase();
            if(copyDatabase(this)){

                mDBHelper.getReadableDatabase();
                if(copyDatabase(this)){
                    Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Copy data error",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        }

        mDatesList = mDBHelper.getListDates();

        final HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        final CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));

        cv.updateCalendar(mDatesList);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();

                Toast.makeText(Calendar.this, df.format(date), Toast.LENGTH_SHORT).show();

                /*Getting the day, month and year to store in database*/

                String ab = df.format(date).toString();
                //change the "," into " " in date
                ab = ab.replaceAll(","," ");
                Log.d("ASFE", "DATE: " + ab);
                //split the date into day, month, and year
                final String[] splited = ab.split("\\s+");
                for(int i = 0; i < splited.length; i++)
                {
                    Log.d("ASFE", "MONTH:" + splited[0] + "  DAY:" + splited[1] + "  YEAR:"+ splited[2] );
                }
                storeday = splited[1].trim();
                //get the year from 2017 to 117 to add into database
                String substring = splited[2].substring(Math.max(splited[2].length() - 2, 0));
                storeyear = ("1" + substring).trim();
                Log.d("ASFE", "YEAR:" + storeyear);

                if(splited[0] == "Jan")
                    storemonth="0";
                else if(splited[0] == "Feb")
                    storemonth = "1";
                else if(splited[0] == "Mar")
                    storemonth = "2";
                else if(splited[0] == "Apr")
                    storemonth = "3";
                else if(splited[0] == "May")
                    storemonth = "4";
                else if(splited[0] == "Jun")
                    storemonth = "5";
                else if(splited[0] == "Jul")
                    storemonth = "6";
                else if(splited[0] == "Aug")
                    storemonth = "7";
                else if(splited[0] == "Sep")
                    storemonth = "8";
                else if(splited[0] == "Oct")
                    storemonth = "9";
                else if(splited[0] == "Nov")
                    storemonth = "10";
                else if(splited[0] == "Dec")
                    storemonth = "11";






                cv.updateCalendar(mDatesList);

                final CharSequence[] items = {"Create Workout", "Saved Workout", "Rest Day"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Calendar.this);
                builder.setTitle("Make your selection");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        if(item == 0)
                        {
                            Intent intent = new Intent(Calendar.this, CreateWorkOut.class);
                            startActivity(intent);
                        }
                        else if (item == 1)
                        {
                            Intent intent = new Intent(Calendar.this, Left.class);
                            startActivity(intent);
                        }
                        else if (item == 2)
                        {
                            //update the database and reload the screen again

                            //Day = storeday
                            //Month = storemonth
                            //Year = storeyear
                            insertDateIntoDB(storeday, storemonth, storeyear);

                            Intent intent = new Intent(Calendar.this, Calendar.class);
                            startActivity(intent);
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }

            @Override
            public void setEvents() {
                cv.updateCalendar(mDatesList);
            }
        });
    }

    private boolean copyDatabase(Context context){
        try{
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DB_NAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while((length=inputStream.read(buff))>0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.v("MainActivity", "DB copied" );
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected void insertDateIntoDB(String sday, String smonth, String syear) {

        //need to create the function in databasehelper
        String usern = "Jonathan";
        String wid = "0";
        String ex = "Rest";
        String set ="0";
        String rep = "0";

        boolean isInserted = mDBHelper.insertDataDate(usern, wid, ex, set, rep,sday, smonth, syear);

        if(isInserted = true)
            Log.d("HELLO", "Success");
        else
            Log.d("BYE", "Fail");



    }

}