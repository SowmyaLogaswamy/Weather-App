package com.example.guest.weatherapp.models;

/**
 * Created by michaeldunlap on 9/13/17.
 */

public class ForecastDay {
    private String mCityName;
    private double mTempDay;
    private String mFormattedDate;

    public ForecastDay(String cityName, double tempDay, String formattedDate) {

        this.mCityName = cityName;
        this.mTempDay = tempDay;
        this.mFormattedDate = formattedDate;
    }

    public String getCityName() { return mCityName; }

    public double getTempDay() {
        return mTempDay;
    }

    public String getFormattedDate() { return mFormattedDate; }
}
