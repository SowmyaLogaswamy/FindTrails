package com.findtrails.findtrails.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.findtrails.findtrails.Constants;
import com.findtrails.findtrails.R;
import com.findtrails.findtrails.models.Trail;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TrailDetailFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.trailNameTextView) TextView mNameLabel;
    @Bind(R.id.detailDescriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.detailDirectionTextView) TextView mDirectionsLabel;
    @Bind(R.id.websiteTextView) TextView mUrlLabel;
    @Bind(R.id.saveTrailButton) TextView mSaveTrailButton;

    private Trail mTrail;

    public static TrailDetailFragment newInstance(Trail trail) {

        TrailDetailFragment trailDetailFragment = new TrailDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("trail", Parcels.wrap(trail));
        trailDetailFragment.setArguments(args);
        return trailDetailFragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTrail = Parcels.unwrap(getArguments().getParcelable("trail"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trail_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mTrail.getName());
        mDescriptionLabel.setText(mTrail.getDescription());
        mDirectionsLabel.setText(mTrail.getDirections());
        //mUrlLabel.setText(mTrail.getUrl());

        mUrlLabel.setOnClickListener(this);

        mSaveTrailButton.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        if (v == mUrlLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mTrail.getUrl()));
            startActivity(webIntent);
        }

        if (v == mSaveTrailButton) {
            DatabaseReference trailRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_TRAILS);
            trailRef.push().setValue(mTrail);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }


    }
}
