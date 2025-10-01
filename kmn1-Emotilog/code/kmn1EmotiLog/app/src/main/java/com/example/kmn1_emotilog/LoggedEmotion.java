package com.example.kmn1_emotilog;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoggedEmotion {
//This class creates an object of a emoticon that was clicked and saves the date of when its clicked.
    private Emotion emotion;
    private String timeStamp;
    private LocalTime hms;

    @RequiresApi(api = Build.VERSION_CODES.O)
    LoggedEmotion(Emotion e, String t) {
        this.emotion = e;
        this.timeStamp = t;
        this.hms = LocalTime.now();
    }

//    Basic getters and setters
    public String getEmotionDesc()
    {
        return emotion.getDescription();
    }

    public String getEmotionName() {
        return emotion.getEmoji();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate() {
        return timeStamp;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hmsTime = hms.format(myFormatObj);
        return "Logged " + emotion.getDescription() + " on " + timeStamp + " at " + hmsTime;
    }
}
