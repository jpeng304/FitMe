package com.example.fitme.fitme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fitme.fitme.dbget.Exercise_Type;
import com.example.fitme.fitme.dbget.ListTypeAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Jonathan on 2/11/2017.
 */

public class Choose_Type extends Activity {
    ListView listView;
    int body_part;

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
        body_part = intent.getExtras().getInt("bodyType");

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
        myTypeList = mDBHelper.getExc_type();
        Tadapter = new ListTypeAdapter(this, myTypeList);
        listView.setAdapter(Tadapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                if(itemPosition== 0) //Body
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("bodyType", body_part);
                    intent.putExtra("workType", itemPosition);
                    startActivity(intent);
                }
                else if(itemPosition == 1) //CABLE
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("bodyType", body_part);
                    intent.putExtra("workType", itemPosition);
                    startActivity(intent);
                }
                else if(itemPosition == 2) //Freeweight
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("bodyType", body_part);
                    intent.putExtra("workType", itemPosition);
                    startActivity(intent);
                }
                else if(itemPosition == 3) //Machine
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("bodyType", body_part);
                    intent.putExtra("workType", itemPosition);
                    startActivity(intent);
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
