package com.example.kmn1_emotilog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.kmn1_emotilog.Emotion;
import com.example.kmn1_emotilog.R;

public class addEmojiFragment extends DialogFragment {
    interface AddEmotionDialogListener {
        void addEmotion(Emotion emotion);
    }
    private AddEmotionDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddEmotionDialogListener) {
            listener = (AddEmotionDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_emoji, null);



        EditText editEmotionEmoji = view.findViewById(R.id.edit_text_emotion);
        EditText editEmotionDesc = view.findViewById(R.id.edit_text_emotion_description);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


        return builder
                .setView(view)
                .setTitle("Add an emotion")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String emotionEmoji = editEmotionEmoji.getText().toString();
                    String emotionDescription = editEmotionDesc.getText().toString();
                    listener.addEmotion(new Emotion(emotionEmoji, emotionDescription));
                }).create();
    }

}