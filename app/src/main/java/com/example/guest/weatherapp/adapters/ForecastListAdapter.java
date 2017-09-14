package com.example.guest.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.weatherapp.R;
import com.example.guest.weatherapp.models.ForecastDay;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 9/14/17.
 */

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastDayViewHolder> {

    private ArrayList<ForecastDay> mForecast = new ArrayList<>();
    private Context mContext;

    public ForecastListAdapter(Context context, ArrayList <ForecastDay> forecast) {

        mContext = context;
        mForecast = forecast;

    }

    @Override
    public ForecastListAdapter.ForecastDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item, parent, false);
        ForecastDayViewHolder viewHolder = new ForecastDayViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastListAdapter.ForecastDayViewHolder holder, int position) {
        holder.bindForecastDay(mForecast.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecast.size();
    }



    public class ForecastDayViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tempDayTextView) TextView mTempDayTextView;
        private Context mContext;

        public ForecastDayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

        }

        public void bindForecastDay(ForecastDay forecastDay) {
            mTempDayTextView.setText("Daily Temperature Forecast: " + forecastDay.getTempDay());
        }
    }


}
