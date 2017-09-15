package com.findtrails.findtrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TrailsActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

//    private String[] trails = new String[]{"Discovery Park", "Pike Place Market",
//            "The Washington Park Arbore", "Green Lake Loop Trail", "Seattle Waterfront Pathway", "Seward Park",
//            "Mount Rosa Trail", "Burke Gilman Trail", "Alki Trail", "Foster Island Trail",
//            "Interurban Trail", "Camp Long Trails", "Columbine Trail",
//            "Tollantusky Trail", "Hensley Settlement"};

    public ArrayList<Trail> mTrails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
//        mListView.setAdapter(adapter);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String trail = ((TextView) view).getText().toString();
//                Intent intent = new Intent(TrailsActivity.this, TrailDetailActivity.class);
//
//                intent.putExtra("trailName", trail);
//
//                startActivity(intent);
//            }
//        });

        mLocationTextView.setText("Yayy!! We have found you trails near " + location);
        getTrails(location);

    }

    private void getTrails(String location) {
        final AllTrailsService allTrailsService = new AllTrailsService();

        allTrailsService.exploreTrails(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mTrails = AllTrailsService.processResults(response);

                TrailsActivity.this.runOnUiThread(new Runnable() {

                                                      @Override
                                                      public void run() {
                                                          String[] trailNames = new String[mTrails.size()];
                                                          for (int i = 0; i < trailNames.length; i++) {
                                                              trailNames[i] = mTrails.get(i).getName();

                                                          }

                                                          ArrayAdapter adapter = new ArrayAdapter(TrailsActivity.this,
                                                                  android.R.layout.simple_list_item_1, trailNames);
                                                          mListView.setAdapter(adapter);

                                                          for (Trail trail : mTrails) {
                                                              Log.d(TAG, "City: " + trail.getCity());
                                                              Log.d(TAG, "State: " + trail.getState());
                                                              Log.d(TAG, "Name: " + trail.getName());
                                                              Log.d(TAG, "Direction: " + trail.getDirections());
                                                          }

                                                      }
                                                  });

//                try {
//                    String jsonData = response.body().string();
//                    if (response.isSuccessful()) {
//                        Log.v(TAG, jsonData);
//                        mTrails = allTrailsService.processResults(response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

        });
    }
}
