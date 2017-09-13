package com.example.guest.weatherapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button mGetForecastButton;
    private EditText mLocationEditText;
    private TextView mWeatherMainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherMainTextView = (TextView) findViewById(R.id.weatherMainTextView);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/sansation-bold.ttf");
        mWeatherMainTextView.setTypeface(ostrichFont);

        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mGetForecastButton = (Button) findViewById(R.id.getForecastButton);
        mGetForecastButton.setTransformationMethod(null);

        mGetForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}
