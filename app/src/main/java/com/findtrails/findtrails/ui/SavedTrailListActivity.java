package com.findtrails.findtrails.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.findtrails.findtrails.Constants;
import com.findtrails.findtrails.R;
import com.findtrails.findtrails.adapters.FirebaseTrailViewHolder;
import com.findtrails.findtrails.models.Trail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedTrailListActivity extends AppCompatActivity {
    private DatabaseReference mTrailReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trails);
        ButterKnife.bind(this);

        mTrailReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TRAILS);
        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Trail, FirebaseTrailViewHolder>
                (Trail.class, R.layout.trails_list_item, FirebaseTrailViewHolder.class,
                        mTrailReference) {

            @Override
            protected void populateViewHolder(FirebaseTrailViewHolder viewHolder,
                                              Trail model, int position) {
                viewHolder.bindTrail(model);
            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}