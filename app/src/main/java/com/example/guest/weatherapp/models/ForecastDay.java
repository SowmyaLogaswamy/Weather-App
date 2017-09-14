package com.example.guest.weatherapp.models;

/**
 * Created by michaeldunlap on 9/13/17.
 */

public class ForecastDay {
    private double mTempDay;
    private long mTimestamp;

    public ForecastDay(double tempDay, long timestamp) {

        this.mTempDay = tempDay;
        this.mTimestamp = timestamp;
    }

    public double getTempDay() {
        return mTempDay;
    }

    public long getTimestamp() { return mTimestamp; }
}
