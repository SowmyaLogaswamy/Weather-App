package com.example.guest.weatherapp;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.guest.weatherapp.ui.ForecastActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Guest on 9/13/17.
 */

public class ForecastActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<ForecastActivity> activityTestRule =
            new ActivityTestRule<>(ForecastActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectForecastString() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String forecastString = "Day 1";
        onData(anything())
                .inAdapterView(withId(R.id.forecastListView))
                .atPosition(0)
                .perform(click());
        onView(withText(forecastString)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(forecastString)));
    }
}
