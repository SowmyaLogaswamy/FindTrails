package com.findtrails.findtrails;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.findtrails.findtrails.ui.MainActivity;
import com.findtrails.findtrails.ui.TrailsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView appNameTextView = (TextView) activity.findViewById(R.id.appNameTextView);
        assertTrue("Find Trails".equals(appNameTextView.getText().toString()));
    }
    @Test
    public void secondActivityStarted() {
        activity.findViewById(R.id.exploreButton).performClick();
        Intent expectedIntent = new Intent(activity, TrailsActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}