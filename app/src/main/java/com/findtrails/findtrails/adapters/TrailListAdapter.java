package com.findtrails.findtrails.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.findtrails.findtrails.R;
import com.findtrails.findtrails.ui.TrailDetailActivity;
import com.findtrails.findtrails.models.Trail;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrailListAdapter extends RecyclerView.Adapter<TrailListAdapter.TrailViewHolder> {
    private ArrayList<Trail> mTrails = new ArrayList<>();
    private Context mContext;

    public TrailListAdapter (Context context, ArrayList<Trail> trails) {
        mContext = context;
        mTrails = trails;
    }
    @Override
    public TrailListAdapter.TrailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trails_list_item, parent, false);
        TrailViewHolder viewHolder = new TrailViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(TrailListAdapter.TrailViewHolder holder, int position) {
        holder.bindTrail(mTrails.get(position));
    }
    @Override
    public int getItemCount() {
        return mTrails.size();
    }

    public class TrailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.trailNameTextView) TextView mNameTextView;
        @Bind(R.id.trailDescriptionTextView) TextView mTrailDescriptionTextView;
        @Bind(R.id.trailDirectionsTextView) TextView mTrailDirectionsTextView;
        @Bind(R.id.trailUrlTextView) TextView mTrailUrlTextView;
        private Context mContext;

        public TrailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, TrailDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("trails", Parcels.wrap(mTrails));
            mContext.startActivity(intent);
        }


        public void bindTrail(Trail trail) {
            mNameTextView.setText(trail.getName());

            if(trail.getDescription() == "null") {
                mTrailDescriptionTextView.setText("Description:    No description available");
            } else {
                mTrailDescriptionTextView.setText("Description:    " + trail.getDescription());
            }

            if(trail.getDirections() == "null") {
                mTrailDirectionsTextView.setText("Directions:   No directions available");
            } else {
                mTrailDirectionsTextView.setText("Directions:    " + trail.getDirections());
            }

            mTrailUrlTextView.setText(trail.getUrl());
        }
    }
}
