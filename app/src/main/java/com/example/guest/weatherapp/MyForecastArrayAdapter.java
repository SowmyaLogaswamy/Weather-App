package com.example.guest.weatherapp;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Guest on 9/13/17.
 */

public class MyForecastArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mForecast;
    private String[] mConditions;

    public MyForecastArrayAdapter(Context mContext, int resource, String[] mForecast, String[] mConditions) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mForecast = mForecast;
        this.mConditions = mConditions;
    }

    @Override
    public Object getItem(int position) {
        String forecastString = mForecast[position];
        String condition = mConditions[position];
        return String.format("%s\nCondition: %s", forecastString, condition);
    }

    @Override
    public int getCount() {
        return mForecast.length;
    }
}
