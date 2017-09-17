package com.findtrails.findtrails.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.findtrails.findtrails.R;
import com.findtrails.findtrails.models.Trail;
import java.util.ArrayList;
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

    public class TrailViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.trailNameTextView) TextView mNameTextView;
        @Bind(R.id.trailDescriptionTextView) TextView mtrailDescriptionTextView;
        private Context mContext;

        public TrailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindTrail(Trail trail) {
            mNameTextView.setText(trail.getName());
            mtrailDescriptionTextView.setText(trail.getDirections());

        }
    }
}
