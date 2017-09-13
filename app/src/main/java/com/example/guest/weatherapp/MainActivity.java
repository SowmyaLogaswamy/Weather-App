package com.example.guest.weatherapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.getForecastButton) Button mGetForecastButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.weatherMainTextView) TextView mWeatherMainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/sansation-bold.ttf");
        mWeatherMainTextView.setTypeface(ostrichFont);


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
