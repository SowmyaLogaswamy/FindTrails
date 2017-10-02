package com.findtrails.findtrails.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.findtrails.findtrails.Constants;
import com.findtrails.findtrails.R;
import com.findtrails.findtrails.models.Trail;
import com.findtrails.findtrails.ui.TrailListActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseTrailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    View mView;
    Context mContext;

    public FirebaseTrailViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindTrail(Trail trail) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.trailNameTextView);
        TextView descriptionTextView = (TextView) mView.findViewById(R.id.trailDescriptionTextView);
        TextView directionTextView = (TextView) mView.findViewById(R.id.trailDirectionsTextView);


        nameTextView.setText(trail.getName());
        descriptionTextView.setText("Description: " + trail.getDescription());
        directionTextView.setText("Directions: " + trail.getDirections());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Trail> trails = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TRAILS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    trails.add(snapshot.getValue(Trail.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, TrailListActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("trails", Parcels.wrap(trails));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
