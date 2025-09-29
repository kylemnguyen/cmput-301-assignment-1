package com.example.kmn1_emotilog;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserLog {

    private Map<String, Integer> userSummary;
    private ArrayList<LoggedEmotion> userLog;

    private String currentDay;

    public UserLog() {
        userSummary = new HashMap<>();
        userLog = new ArrayList<>();
    }

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showSummary() {
        for(LoggedEmotion e: userLog) {
            System.out.println(e.toString());
        }
        for(Map.Entry<String, Integer> entry: userSummary.entrySet()) {
            System.out.println("Logged " + entry.getKey() + ": " + entry.getValue() + " time(s).");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSummary() {

        setCurrentDay();
        System.out.println("THE CURRENT DATE IS: " + currentDay);

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSummaryIndepth() {


        setCurrentDay();
        System.out.println("THE CURRENT DATE IS: " + currentDay);

        String s = "";
        for(LoggedEmotion e : userLog) {
            if(currentDay.equals(e.getDate())) {
                s = s + e.toString() + "\n";
            }

        }
        return s;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCurrentDay() {
        LocalDateTime tempDay = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        currentDay = tempDay.format(myFormatObj);
    }


}
