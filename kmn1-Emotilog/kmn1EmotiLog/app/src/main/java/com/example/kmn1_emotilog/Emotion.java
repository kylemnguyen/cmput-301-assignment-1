package com.example.kmn1_emotilog;

import androidx.annotation.NonNull;

import java.io.Serializable;
public class Emotion implements Serializable {

    private String emoji;
    private String description;
    public Emotion(String emoji, String desc) {

        this.emoji = emoji;
        this.description = desc;

    }


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
