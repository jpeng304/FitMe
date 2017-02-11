package com.example.fitme.fitme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Jonathan on 2/5/2017.
 */

public class MainScreen extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
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
