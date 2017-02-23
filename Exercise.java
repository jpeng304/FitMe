package com.example.fitme.fitme;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fitme.fitme.dbget.Exercise_Type;
import com.example.fitme.fitme.dbget.ListTypeAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonathan on 2/11/2017.
 */

public class Exercise extends Activity {
    ListView listView;
    final Context context = this;
    int Type;
    int Body;

    private ListTypeAdapter Tadapter;
    private List<Exercise_Type> myTypeList;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout);

        listView = (ListView)findViewById(R.id.exerciseList);
        mDBHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        Type = intent.getExtras().getInt("workType");
        Body = intent.getExtras().getInt("bodyType");


        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DB_NAME);

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
        if(Body == 0 && Type == 0)
        {
            myTypeList = mDBHelper.getExc_name_arms_body();
        }
        else if (Body == 0 && Type == 1)
        {
            myTypeList = mDBHelper.getExc_name();
        }
        Tadapter = new ListTypeAdapter(this, myTypeList);
        listView.setAdapter(Tadapter);


        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                if (itemPosition == 0) {
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.workout_amount_prompt, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                    final EditText userInput = (EditText) promptsView.
                            findViewById(R.id.editTextDialogUserInput);

                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("✓",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            Intent intent = new Intent(Exercise.this, CreateWorkOut.class);
                                            //intent.putExtra("savedName", workoutName);
                                            startActivity(intent);

                                        }
                                    })
                            .setNegativeButton("X",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
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
}
