package com.example.kmn1_emotilog;

public class CurrentDate {


//    This is a basic currentDate object for each day (up to 7 days
//    prior from the current date of the application being open

    String date;
    String year;

    CurrentDate(String d, String y ) {
        this.date = d;
        this.year = y;
    }

//  vv  Basic getters & setters and toString vv

    public void setDate(String d) {
        this.date = d;
    }

    public String getDate() {
        return this.date;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String y) {
        this.year = y;
    }


    public String toString() {
        return this.date + "-" + this.year;
    }
}
