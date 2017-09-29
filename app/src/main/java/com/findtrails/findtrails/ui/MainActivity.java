package com.findtrails.findtrails.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.findtrails.findtrails.Constants;
import com.findtrails.findtrails.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.savedTrailsButton) Button mSavedTrailsButton;

    private ValueEventListener mSearchedLocationReferenceListener;

    private DatabaseReference mSearchedLocationReference;
    //private SharedPreferences mSharedPreferences;
   // private SharedPreferences.Editor mEditor;

    @Bind(R.id.exploreButton) Button mExploreButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);



        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }


        });



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

      //  mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // mEditor = mSharedPreferences.edit();

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mAppNameTextView.setTypeface(pacificoFont);

        mExploreButton.setOnClickListener(this);
        mSavedTrailsButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mExploreButton) {
            String location = mLocationEditText.getText().toString();

            saveLocationToFirebase(location);
          //  if(!(location).equals("")) {
          //     addToSharedPreferences(location);
          //  }
            Intent intent = new Intent(MainActivity.this, TrailsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }

        if (v == mSavedTrailsButton) {
            Intent intent = new Intent(MainActivity.this, SavedTrailListActivity.class);
            startActivity(intent);
        }
    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }
//    public void onClick(View v) {
//        if (v == mExploreButton) {
//            String location = mLocationEditText.getText().toString();
//
//            if (location.length() == 0) {
//                Toast.makeText(MainActivity.this, "Enter a valid city name!", Toast.LENGTH_LONG).show();
//            } else {
//                addToSharedPreferences(location);
//                Intent intent = new Intent(MainActivity.this, TrailsActivity.class);
//                intent.putExtra("location", location);
//                startActivity(intent);
//            }
//        }
//    }

      //  private void addToSharedPreferences(String location) {
       //     mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
       // }
    }


