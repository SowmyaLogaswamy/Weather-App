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

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity {

    public static final String TAG = ForecastActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.forecastListView) ListView mForecastListView;
    private String[] forecast =new String[]{
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private String[] conditions =new String[]{
            "sunny", "partly cloudy", "sunny", "fog", "smoky", "unbearable", "rainy"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);


        MyForecastArrayAdapter adapter = new MyForecastArrayAdapter(this, android.R.layout.simple_list_item_1, forecast, conditions);
        mForecastListView.setAdapter(adapter);


        mForecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String forecastString = ((TextView) view).getText().toString();
                Toast.makeText(ForecastActivity.this, forecastString, Toast.LENGTH_LONG).show();
            }
        });


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
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });

    }
}
