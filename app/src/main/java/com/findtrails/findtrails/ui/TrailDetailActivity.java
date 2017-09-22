package com.findtrails.findtrails.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.findtrails.findtrails.R;
import com.findtrails.findtrails.adapters.TrailPagerAdapter;
import com.findtrails.findtrails.models.Trail;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrailDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private TrailPagerAdapter adapterViewPager;
    ArrayList<Trail> mTrails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_detail);
        ButterKnife.bind(this);

        mTrails = Parcels.unwrap(getIntent().getParcelableExtra("trails"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new TrailPagerAdapter(getSupportFragmentManager(), mTrails);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}