package com.example.guest.weatherapp.services;

import android.util.Log;

import com.example.guest.weatherapp.Constants;
import com.example.guest.weatherapp.models.ForecastDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 9/13/17.
 */

public class OpenWeatherService {
    public static final String TAG = OpenWeatherService.class.getSimpleName();

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

    public ArrayList<ForecastDay> processResults(Response response) {
        ArrayList<ForecastDay> forecast = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject openWeatherJSON = new JSONObject(jsonData);
                JSONArray forecastJSON = openWeatherJSON.getJSONArray("list");
                String cityName = openWeatherJSON.getJSONObject("city").getString("name");
                Log.v(TAG, cityName);


                for (int i = 0; i < forecastJSON.length(); i++) {
                    JSONObject forecastDayJSON = forecastJSON.getJSONObject(i);

                    double tempDay = forecastDayJSON.getJSONObject("temp").getDouble("day");

                    long timestamp = forecastDayJSON.getLong("dt");
                    Date date = new Date(timestamp*1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM");
                    String formattedDate = sdf.format(date);

                    ForecastDay forecastDay = new ForecastDay(cityName, tempDay, formattedDate);
                    forecast.add(forecastDay);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return forecast;
    }

//    public String processCityName(Response response) {
//        String cityName = "";
//
//        try {
//            String jsonData = response.body().string();
//            if (response.isSuccessful()) {
//                JSONObject openWeatherJSON = new JSONObject(jsonData);
//                cityName += openWeatherJSON.getJSONObject("city").getString("name");
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//
//        return cityName;
//    }
}
