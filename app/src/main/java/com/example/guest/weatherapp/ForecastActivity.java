package com.example.guest.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity {

    public static final String TAG = ForecastActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.forecastListView) ListView mForecastListView;
//    private String[] forecast =new String[]{
//            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
//    private String[] conditions =new String[]{
//            "sunny", "partly cloudy", "sunny", "fog", "smoky", "unbearable", "rainy"};

    public ArrayList<ForecastDay> mForecast = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);


//        MyForecastArrayAdapter adapter = new MyForecastArrayAdapter(this, android.R.layout.simple_list_item_1, forecast, conditions);
//        mForecastListView.setAdapter(adapter);


//        mForecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String forecastString = ((TextView) view).getText().toString();
//                Toast.makeText(ForecastActivity.this, forecastString, Toast.LENGTH_LONG).show();
//            }
//        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here is the forecast for " + location);

        getForecast(location);

    }

    private void getForecast(String location) {
        final OpenWeatherService openWeatherService = new OpenWeatherService();

        openWeatherService.findForecast(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mForecast = openWeatherService.processResults(response);

                ForecastActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Double[] forecastTempDays = new Double[mForecast.size()];
                        for (int i = 0; i < forecastTempDays.length; i++) {
                            forecastTempDays[i] = mForecast.get(i).getTempDay();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ForecastActivity.this,
                                android.R.layout.simple_list_item_1, forecastTempDays);
                        mForecastListView.setAdapter(adapter);

                        for (ForecastDay forecastDay : mForecast) {
                            Log.d(TAG, "Daily Temp: " + Double.toString(forecastDay.getTempDay()));
                        }
                    }
                });
            }

        });

    }
}
