package com.example.kmn1_emotilog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kmn1_emotilog.Emotion;
import com.example.kmn1_emotilog.R;

import java.util.ArrayList;

public class EmotionArrayAdapter extends ArrayAdapter<Emotion> {
//    This is a an array adapter class that sets up the gridview of the emoticons the user has made
    public EmotionArrayAdapter(Context context, ArrayList<Emotion> cities) {
        super(context, 0, cities);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;


        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content,
                    parent, false);
        } else {
            view = convertView;
        }


        Emotion city = getItem(position);
        TextView cityName = view.findViewById(R.id.emotion_emoji);
        TextView provinceName = view.findViewById(R.id.emotion_description);
        cityName.setText(city.getEmoji());
        provinceName.setText(city.getDescription());
        return view;


    }
}

