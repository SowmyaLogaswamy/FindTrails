package com.findtrails.findtrails.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.findtrails.findtrails.models.Trail;
import com.findtrails.findtrails.ui.TrailDetailFragment;

import java.util.ArrayList;

public class TrailPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Trail> mTrails;

    public TrailPagerAdapter(FragmentManager fm, ArrayList<Trail> trails) {
        super(fm);
        mTrails = trails;
    }


    @Override
    public Fragment getItem(int position) {
        return TrailDetailFragment.newInstance(mTrails.get(position));
    }

    @Override
    public int getCount() {
        return mTrails.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTrails.get(position).getName();
    }
}
