package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    //here is mainactivity
    // loading view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = new Intent(MainActivity.this, PersonalData.class);
        myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);


    }
}
