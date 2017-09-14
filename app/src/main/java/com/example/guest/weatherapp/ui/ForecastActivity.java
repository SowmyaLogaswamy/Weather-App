package com.example.guest.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.weatherapp.R;
import com.example.guest.weatherapp.adapters.ForecastListAdapter;
import com.example.guest.weatherapp.models.ForecastDay;
import com.example.guest.weatherapp.services.OpenWeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity {
    public static final String TAG = ForecastActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;

    private ForecastListAdapter mAdapter;

    public ArrayList<ForecastDay> mForecast = new ArrayList<>();
    public String mCityName = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
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
            public void onResponse(Call call, Response response) {
                mForecast = openWeatherService.processResults(response);
//                mCityName = openWeatherService.processCityName(response);
                String mCityName = "A city";
                ForecastActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new ForecastListAdapter(getApplicationContext(), mForecast);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ForecastActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        mLocationTextView.setText("Here is the forecast for " + mForecast.get(0).getCityName());
                    }
                });
            }


        });

    }
}
