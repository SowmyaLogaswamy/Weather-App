package com.example.guest.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mGetForecastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetForecastButton = (Button) findViewById(R.id.getForecastButton);
        mGetForecastButton.setTransformationMethod(null);

    }
}
