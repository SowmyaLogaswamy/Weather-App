package com.example.guest.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mGetForecastButton;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mGetForecastButton = (Button) findViewById(R.id.getForecastButton);
        mGetForecastButton.setTransformationMethod(null);

        mGetForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);
                Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
                startActivity(intent);
            }
        });
    }
}
