package com.example.guest.weatherapp.models;

/**
 * Created by michaeldunlap on 9/13/17.
 */

public class ForecastDay {
    private double mTempDay;
    private String mFormattedDate;

    public ForecastDay(double tempDay, String formattedDate) {

        this.mTempDay = tempDay;
        this.mFormattedDate = formattedDate;
    }

    public double getTempDay() {
        return mTempDay;
    }

    public String getFormattedDate() { return mFormattedDate; }
}
