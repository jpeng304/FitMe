package com.example.fitme.fitme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Jonathan on 2/11/2017.
 */

public class Choose_Type extends Activity {
    ListView listView;
    String work_part;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout);

        Intent intent = getIntent();
        work_part = intent.getExtras().getString("workType");

        listView = (ListView) findViewById(R.id.exerciseList);
        String[] type = {"Body","Cable","Machine", "Free-Weight"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, type);
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

                if(itemValue == "Body")
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("workUse", itemValue);
                    intent.putExtra("workType", work_part);
                    startActivity(intent);
                }
                else if(itemValue == "Cable")
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("workUse", itemValue);
                    intent.putExtra("workType", work_part);
                    startActivity(intent);
                }
                else if(itemValue == "Machine")
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("workUse", itemValue);
                    intent.putExtra("workType", work_part);
                    startActivity(intent);
                }
                else if(itemValue == "Free-Weight")
                {
                    Intent intent = new Intent(Choose_Type.this, Exercise.class);
                    intent.putExtra("workUse", itemValue);
                    intent.putExtra("workType", work_part);
                    startActivity(intent);
                }
            }

        });
    }
}
