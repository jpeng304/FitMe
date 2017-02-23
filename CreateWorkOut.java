package com.example.fitme.fitme;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Jonathan on 2/11/2017.
 */

public class CreateWorkOut extends Activity {

    ListView listView;
    String workout_name;
    Button bDone;
    Button bCancel;
    private ListProductAdapter adapter;
    private List<Product> mProductList;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_workout);
        listView = (ListView)findViewById(R.id.list);
        mDBHelper = new DatabaseHelper(this);

        bDone = (Button)findViewById(R.id.bDone);
        bCancel = (Button)findViewById(R.id.bCancel);
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
        mProductList = mDBHelper.getListProductExName();
        adapter = new ListProductAdapter(this, mProductList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                if(itemPosition== 0) //Arms
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("bodyType", itemPosition);
                    startActivity(intent);
                }
                else if(itemPosition == 1) //Chest
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("bodyType", itemPosition);
                    startActivity(intent);
                }
                else if(itemPosition == 2) //Legs
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("bodyType", itemPosition);
                    startActivity(intent);
                }

            }
        });

        bCancel.setOnClickListener( new View.OnClickListener(){
            public void onClick(View V){
                Intent intent = new Intent(CreateWorkOut.this, MainScreen.class);
                startActivity(intent);
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
