package com.example.guest.weatherapp;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 9/13/17.
 */

public class OpenWeatherService {

    public static void findForecast(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().
                build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OPEN_WEATHER_BASE_URL).newBuilder();

        urlBuilder
                .addQueryParameter(Constants.OPEN_WEATHER_LOCATION_QUERY_PARAMETER, location)
                .addQueryParameter(Constants.OPEN_WEATHER_UNITS_QUERY_PARAMETER, "imperial")
                .addQueryParameter(Constants.OPEN_WEATHER_APP_ID_PARAMETER, Constants.OPEN_WEATHER_APP_ID );

        String url = urlBuilder.build().toString();

        Log.v("Url looks like this: ", url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
