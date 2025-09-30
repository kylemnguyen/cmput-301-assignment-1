package com.example.kmn1_emotilog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DateArrayAdapter extends ArrayAdapter<CurrentDate> {
    public DateArrayAdapter(Context context, ArrayList<CurrentDate> calendar) {
        super(context, 0, calendar);
    }


//    vv this is the adapter that sets up the gridview for the the calendar

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;


        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.date_layout,
                    parent, false);
        } else {
            view = convertView;
        }


        CurrentDate day = getItem(position);
        TextView currentDayAndMonth = view.findViewById(R.id.day_and_month);
        TextView currentYear = view.findViewById(R.id.year);
        currentDayAndMonth.setText(day.getDate());
        currentYear.setText(day.getYear());
        return view;


    }
}