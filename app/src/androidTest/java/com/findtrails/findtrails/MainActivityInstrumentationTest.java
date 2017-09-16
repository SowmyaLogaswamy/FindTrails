package com.findtrails.findtrails;

/**
 * Created by Guest on 9/8/17.
 */

import android.support.test.rule.ActivityTestRule;

import com.findtrails.findtrails.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.locationEditText)).perform(typeText("Portland"))
                .check(matches(withText("Portland")));
    }

    @Test
    public void locationIsSentToTrailsActivity() {
        String location = "Portland";
        onView(withId(R.id.locationEditText)).perform(typeText(location));
        onView(withId(R.id.exploreButton)).perform(click());
        onView(withId(R.id.locationTextView)).check(matches
                (withText("We have found you trails near " + location)));
    }



}