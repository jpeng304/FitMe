package com.example.fitme.fitme;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jonathan on 2/11/2017.
 */

public class CreateWorkOut extends Activity {

    ListView listView;
    String workout_name;
    Button bDone;
    Button bCancel;
    //private TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_workout);

        bDone = (Button)findViewById(R.id.bDone);
        bCancel = (Button)findViewById(R.id.bCancel);

        listView = (ListView) findViewById(R.id.list);
        String[] exercise = {"Abs","Arms","Back","Chest","Legs"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, exercise);
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                /*// Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();*/
                if(itemValue == "Abs")
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("workType", itemValue);
                    startActivity(intent);
                }
                else if(itemValue == "Arms")
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("workType", itemValue);
                    startActivity(intent);
                }
                else if(itemValue == "Abs")
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("workType", itemValue);
                    startActivity(intent);
                }
                else if(itemValue == "Back")
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("workType", itemValue);
                    startActivity(intent);
                }
                else if(itemValue == "Chest")
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("workType", itemValue);
                    startActivity(intent);
                }
                else if(itemValue == "Legs")
                {
                    Intent intent = new Intent(CreateWorkOut.this, Choose_Type.class);
                    intent.putExtra("workType", itemValue);
                    startActivity(intent);
                }

            }

        });


        /*tvName = (TextView) findViewById(R.id.tvName);
        Intent intent = getIntent();
        workout_name = intent.getExtras().getString("savedName");
        tvName.setText(workout_name);*/
        bCancel.setOnClickListener( new View.OnClickListener(){

            public void onClick(View V){
                Intent intent = new Intent(CreateWorkOut.this, MainScreen.class);
                startActivity(intent);
            }
        });

    }

}
