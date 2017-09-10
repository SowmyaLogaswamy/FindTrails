package com.findtrails.findtrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrailsActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] trails = new String[] {"Discovery Park", "Pike Place Market",
            "The Washington Park Arbore", "Green Lake Loop Trail", "Seattle Waterfront Pathway", "Seward Park",
            "Mount Rosa Trail", "Burke Gilman Trail", "Alki Trail", "Foster Island Trail",
            "Interurban Trail", "Camp Long Trails", "Columbine Trail",
            "Tollantusky Trail", "Hensley Settlement"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);

       ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, trails);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String trail = ((TextView)view).getText().toString();
                Intent intent = new Intent (TrailsActivity.this, TrailDetailActivity.class);

                intent.putExtra("trailName", trail);

                startActivity(intent);
            }
        });

        Intent intent =getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("We have found you trails near " + location);

    }
}
