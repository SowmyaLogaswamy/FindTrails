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

public class TrailsActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mLocationTextView;
    private ListView mListView;

    private String[] trails = new String[] {"Discovery Park", "Pike Place Market",
            "The Washington Park Arbore", "Green Lake Loop Trail", "Seattle Waterfront Pathway", "Seward Park",
            "Mount Rosa Trail", "Burke Gilman Trail", "Alki Trail", "Foster Island Trail",
            "Interurban Trail", "Camp Long Trails", "Columbine Trail",
            "Tollantusky Trail", "Hensley Settlement"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, trails);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String trail = ((TextView)view).getText().toString();
                Log.d(TAG, trail);
                Toast.makeText(TrailsActivity.this, trail, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent =getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("We have found you trails near " + location);

    }
}
