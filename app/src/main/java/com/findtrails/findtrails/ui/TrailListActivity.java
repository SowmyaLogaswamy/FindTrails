package com.findtrails.findtrails.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class TrailListActivity extends AppCompatActivity {
    public static final String TAG = TrailListActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
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
       // Log.d("Shared Pref Location", mRecentAddress);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getTrails(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

                TrailListActivity.this.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      mAdapter = new TrailListAdapter(getApplicationContext(), mTrails);
                      mRecyclerView.setAdapter(mAdapter);
                      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TrailListActivity.this);
                      mRecyclerView.setLayoutManager(layoutManager);
                      mRecyclerView.setHasFixedSize(true);
                      for (int i = 0; i < mTrails.size(); i++) {
                          mLocationTextView.setText("Here are the trails near " + mTrails.get(0).getCity() + ", " + mTrails.get(i).getState());
                      }
                  }
                });
            }

        });
    }
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}
