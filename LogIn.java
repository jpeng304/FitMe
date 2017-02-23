package com.example.fitme.fitme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        final Button btnLog = (Button) findViewById(R.id.btnLog);
        final TextView tvSignUp = (TextView) findViewById(R.id.tvSignUp);


        btnLog.setOnClickListener( new View.OnClickListener(){

            public void onClick(View V){
                Intent intent = new Intent(LogIn.this, MainScreen.class);
                startActivity(intent);
            }
        });

        tvSignUp.setOnClickListener( new View.OnClickListener(){

            public void onClick(View V){
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });
    }


}
