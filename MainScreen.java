package com.example.fitme.fitme;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jonathan on 2/5/2017.
 */

public class MainScreen extends Activity {

    final Context context = this;
    private Button btnStart;
    private GestureDetectorCompat gestureObject;
    String workoutName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        btnStart = (Button) findViewById(R.id.btnStart);
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.workout_name_prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.
                findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        workoutName = userInput.getText().toString();

                                        //Intent intent = new Intent(MainScreen.this, CreateWorkOut.class);
                                        Intent intent = new Intent(MainScreen.this, CreateWorkOut.class);
                                        //intent.putExtra("savedName", workoutName);
                                        startActivity(intent);

                                    }
                                })
                        .setNegativeButton("Cancel",
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
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        //SimpleOnGestureLister is listener for what we want to do and how
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            if(event2.getX() > event1.getX()){
                //left to right swipe
                Intent intent = new Intent(MainScreen.this, Left.class);
                //finish(); //this is to use to stop history for MainScreen class
                startActivity(intent);

            }
            else
            if (event2.getX() < event1.getX()){
                //right to left swipe
                Intent intent = new Intent(MainScreen.this, Right.class);
                //finish(); //this is to use to stop history for MainScreen class
                startActivity(intent);

            }
            return true;

        }
    }
}
