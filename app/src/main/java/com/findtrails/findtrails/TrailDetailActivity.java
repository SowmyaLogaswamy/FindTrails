package com.findtrails.findtrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrailDetailActivity extends AppCompatActivity {
    @Bind(R.id.detailTextView) TextView mDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_detail);
        ButterKnife.bind(this);

        Intent intent =getIntent();
        String trailName = intent.getStringExtra("trailName");
        mDetailTextView.setText(trailName);
    }
}
