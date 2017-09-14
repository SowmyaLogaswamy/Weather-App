package com.example.guest.weatherapp;

import android.os.Build;
import android.widget.ListView;

import com.example.guest.weatherapp.ui.ForecastActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Guest on 9/13/17.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)


public class ForecastActivityTest {
    private ForecastActivity activity;
    private ListView mForecastListView;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(ForecastActivity.class);
        mForecastListView = (ListView) activity.findViewById(R.id.forecastListView);
    }

    @Test
    public void forecasttListViewPopulates() {
        assertNotNull(mForecastListView.getAdapter());
        assertEquals(mForecastListView.getAdapter().getCount(), 7);
    }

}

