package com.findtrails.findtrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrailDetailActivity extends AppCompatActivity {
    @Bind(R.id.detailTextView) TextView mDetailTextView;
    @Bind(R.id.headingTextView) TextView mHeadingTextView;

    private static final Map<String, String> trailMap;
    static
    {
        trailMap = new HashMap<String, String>();
        trailMap.put("Discovery Park", "Discovery Trail is a 1.5 mile loop trail located near Conway, Arkansas and is good for all skill levels. The trail is primarily used for hiking and is accessible year-round.");
        trailMap.put("Pike Place Market", "Pike place Market is a 1.5 mile loop trail located near Conway, Arkansas and is good for all skill levels. The trail is primarily used for hiking and is accessible year-round.");
        trailMap.put("The Washington Park Arbore", "Washington park is a 1.5 mile loop trail located near Conway, Arkansas and is good for all skill levels. The trail is primarily used for hiking and is accessible year-round.");
        trailMap.put("Green Lake Loop Trail", "Green Lake Trail is a 1.5 mile loop trail located near Conway, Arkansas and is good for all skill levels. The trail is primarily used for hiking and is accessible year-round.");
        trailMap.put("Seattle Waterfront Pathway", " Seattle Waterfront pathway is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Seward Park", "Seward Park is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Mount Rosa Trail", "Mount Rosa is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Burke Gilman Trail", " Burke is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Alki Trail", "Alki is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Foster Island Trail", "Foster is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Interurban Trail", "Interurban is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Camp Long Trails", "Camp Long is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Columbine Trail", "Columbine is a 3.9 mile heavily trafficked loop trail located near Seattle, Washington that offers the chance to see wildlife and is good for all skill levels. The trail is primarily used for hiking, walking, and trail running and is best used from May until December. Dogs are also able to use this trail but must be kept on leash.");
        trailMap.put("Tollantusky Trail", "Tollantusky Trail is a 1.5 mile loop trail located near Conway, Arkansas and is good for all skill levels. The trail is primarily used for hiking and is accessible year-round.!");
        trailMap.put("Hensley Settlement", "Hensley Settlement is a 3.6 mile lightly trafficked loop trail located near Big Bear City, California that offers scenic views and is rated as moderate. The trail is primarily used for hiking, walking, and mountain biking and is best used from May until November.Nice trail!!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_detail);
        ButterKnife.bind(this);

        Intent intent =getIntent();
        String trailName = intent.getStringExtra("trailName");
        mHeadingTextView.setText(trailName);

        String trailDetail = trailMap.get(trailName);
        mDetailTextView.setText(trailDetail);

    }
}
