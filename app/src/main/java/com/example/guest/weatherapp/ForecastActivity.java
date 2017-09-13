package com.example.guest.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ForecastActivity extends AppCompatActivity {
    private TextView mLocationTextView;
    private ListView mForecastListView;
    private String[] forecast =new String[]{
            "Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        mForecastListView = (ListView) findViewById(R.id.forecastListView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, forecast);
        mForecastListView.setAdapter(adapter);


        mForecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String forecastString = ((TextView)view).getText().toString();
                Toast.makeText(ForecastActivity.this, forecastString, Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here is the forecast for " + location);

    }
}
