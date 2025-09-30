package com.example.kmn1_emotilog;

import androidx.annotation.NonNull;

import java.io.Serializable;
public class Emotion implements Serializable {


//    Basic emotion class object that holds its emoticon and description
    private String emoji;
    private String description;
    public Emotion(String emoji, String desc) {

        this.emoji = emoji;
        this.description = desc;

    }
// Basic getters and setters vvv
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

}
