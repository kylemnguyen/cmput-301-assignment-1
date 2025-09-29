package com.example.kmn1_emotilog;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggedEmotion {

    private Emotion emotion;
    private LocalDateTime timeStamp;


    @RequiresApi(api = Build.VERSION_CODES.O)
    LoggedEmotion(Emotion e) {
        this.emotion = e;
        this.timeStamp = LocalDateTime.now();
    }

    public String getEmotionDesc()
    {
        return emotion.getDescription();
    }

    public String getEmotionName() {
        return emotion.getEmoji();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = timeStamp.format(myFormatObj);
        System.out.println(formattedDate);
        return formattedDate;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = timeStamp.format(myFormatObj);
        return "Logged " + emotion.getDescription() + " at " + formattedDate;
    }
}
