package com.findtrails.findtrails.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    private TrailListAdapter mAdapter;


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
                                                          mAdapter = new TrailListAdapter(getApplicationContext(), mTrails);
                                                          mRecyclerView.setAdapter(mAdapter);
                                                          RecyclerView.LayoutManager layoutManager =
                                                                  new LinearLayoutManager(TrailsActivity.this);
                                                          mRecyclerView.setLayoutManager(layoutManager);
                                                          mRecyclerView.setHasFixedSize(true);

                                                        // mLocationTextView.setText("Here are the trails near " + mTrails.get(0).getCity());
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
