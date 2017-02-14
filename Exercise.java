package com.example.fitme.fitme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Jonathan on 2/11/2017.
 */

public class Exercise extends Activity {
    ListView listView;
    final Context context = this;
    String Type = "";
    String Use = "";
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout);

        Intent intent = getIntent();
        Type = intent.getExtras().getString("workType");
        Use = intent.getExtras().getString("workUse");

        Log.d("Clicked", "searchList" + Type);
        Log.d("HELLO", "HELLO" + Use);
        listView = (ListView) findViewById(R.id.exerciseList);
        String[] arm_Body = {"Push-Up", "Blah"};
        String[] arm_Free_Weight = {"Dumbell", "Ah"};



        if (Type.equals("Arms")&& Use.equals("Body")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, arm_Body);
            listView.setAdapter(adapter);

        } else if (Type.equals("Arms") && Use.equals("Free-Weight")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, arm_Free_Weight);
            listView.setAdapter(adapter);
        }



        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                if (itemValue == "Push-Up") {
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

}
