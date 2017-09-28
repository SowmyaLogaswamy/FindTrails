package com.findtrails.findtrails.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.findtrails.findtrails.Constants;
import com.findtrails.findtrails.R;
import com.findtrails.findtrails.adapters.TrailListAdapter;
import com.findtrails.findtrails.models.Trail;
import com.findtrails.findtrails.services.AllTrailsService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TrailsActivity extends AppCompatActivity {
    public static final String TAG = TrailsActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    private TrailListAdapter mAdapter;
    public ArrayList<Trail> mTrails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getTrails(location);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentAddress != null) {
            getTrails(mRecentAddress);
        }
        Log.d("Shared Pref Location", mRecentAddress);
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
                      mAdapter = new TrailListAdapter(getApplicationContext(), mTrails);
                      mRecyclerView.setAdapter(mAdapter);
                      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TrailsActivity.this);
                      mRecyclerView.setLayoutManager(layoutManager);
                      mRecyclerView.setHasFixedSize(true);
                      for(int i = 0; i< mTrails.size(); i++) {
                          mLocationTextView.setText("Here are the trails near " + mTrails.get(0).getCity() + ", " + mTrails.get(i).getState());
                      }
                  }
                });
            }

        });
    }
}
