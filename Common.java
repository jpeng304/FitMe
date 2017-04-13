package com.example.campususer.fitme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Aryana on 4/10/2017.
 */

public class Common extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);

        Button bArms = (Button) findViewById(R.id.bArms);
        Button bLegs = (Button) findViewById(R.id.bLegs);
        Button bAbs = (Button) findViewById(R.id.bAbs);
        Button bBack = (Button) findViewById(R.id.bBack);
        Button bChest = (Button) findViewById(R.id.bChest);

        bArms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Common.this, WorkoutPage2.class);
                startActivity(i);
            }
        });
        bLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Common.this, WorkoutPage2.class);
                startActivity(i);
            }
        });

        bAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Common.this, WorkoutPage2.class);
                startActivity(i);
            }
        });
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Common.this, WorkoutPage2.class);
                startActivity(i);
            }
        });
        bChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Common.this, WorkoutPage2.class);
                startActivity(i);
            }
        });
    }
}