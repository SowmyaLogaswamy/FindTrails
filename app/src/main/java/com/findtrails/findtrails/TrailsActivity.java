package com.findtrails.findtrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TrailsActivity extends AppCompatActivity {
    private TextView mLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        Intent intent =getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("We have found you trails near" + location);
    }
}
