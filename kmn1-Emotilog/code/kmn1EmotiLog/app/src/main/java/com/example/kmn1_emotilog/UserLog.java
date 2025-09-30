package com.example.kmn1_emotilog;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserLog {

//    This is a userLog object that stores all emoticons that has been clicked
//    It also creates two version of summaries, one that showcases the frequency and one that shows the full indepth analysis
//    It has a changeable currentdate to have a correct display of emoticons that wer clicked on the selected day


//    Hash map of the frequencies of all emoticons clicked (no matter the date)
    private Map<String, Integer> userSummary;
//    ArrayLst of every logged emoticon
    private ArrayList<LoggedEmotion> userLog;

    private String currentDay;

    public UserLog() {
        userSummary = new HashMap<>();
        userLog = new ArrayList<>();
    }


//    Adds the emoticon clicked to the hashmap and the arraylists
    public void addEmotionToLog(LoggedEmotion le) {

        if(!userSummary.containsKey(le.getEmotionName())) {
            userSummary.put(le.getEmotionName(), 1);
        }
        else {
            int value = userSummary.get(le.getEmotionName());
            value++;

            userSummary.put(le.getEmotionName(), value);

        }

        userLog.add(le);

    }

//    vv This is a frequency summary of emoticons clicked on the specified date.

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSummary() {
        Map<String, Integer> currentSummary = new HashMap<>();


        for(LoggedEmotion e : userLog) {

            if(currentDay.equals(e.getDate())) {

                if(!currentSummary.containsKey(e.getEmotionName())) {
                    currentSummary.put(e.getEmotionName(), 1);
                }
                else {
                    int value = currentSummary.get(e.getEmotionName());
                    value++;

                    currentSummary.put(e.getEmotionName(), value);

                }

            }

        }


        String s = "";
        for(Map.Entry<String, Integer> entry: currentSummary.entrySet()) {

            s = s + "Logged " + entry.getKey() + " : " + entry.getValue() + " time(s).\n" ;
        }

        return s;

    }


//    vv Indepth summary of all events of emoticon click with corresponding date
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSummaryIndepth() {


        String s = "";
        for(LoggedEmotion e : userLog) {
            if(currentDay.equals(e.getDate())) {
                s = s + e.toString() + "\n";
            }

        }
        return s;
    }

//    Sets the currentDate on the day application is openeed
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCurrentDay() {
        LocalDateTime tempDay = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        currentDay = tempDay.format(myFormatObj);
    }

//    Resets date for prior events

    public void setDate(String d) {
        this.currentDay = d;
    }
    public String getCurrentDay() {
        return this.currentDay;
    }


}
