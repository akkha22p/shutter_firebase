package com.example.shutter_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread background = new Thread(){
            @Override
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    Thread.sleep(5000);

                    // After 5 seconds redirect to another intent
                    Intent myIntent = getIntent();
                    myIntent.setClass(MainActivity.this, ExploreActivity.class);
                    startActivity(myIntent);

                    finish();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Going to Main Activity", Toast.LENGTH_LONG).show();
                }

            }
        };

        background.start();
    }
}
