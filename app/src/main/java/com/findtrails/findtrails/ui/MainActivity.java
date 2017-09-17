package com.findtrails.findtrails.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.findtrails.findtrails.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.exploreButton) Button mExploreButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mAppNameTextView.setTypeface(pacificoFont);
        mExploreButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v == mExploreButton) {
                    String location = mLocationEditText.getText().toString();
                   if(location.length() == 0) {
                       Toast.makeText(MainActivity.this, "Enter a valid city name!", Toast.LENGTH_LONG).show();
                   }
                    else {
                       Intent intent = new Intent(MainActivity.this, TrailsActivity.class);
                       intent.putExtra("location", location);
                       startActivity(intent);
                   }
                }
            }
}

