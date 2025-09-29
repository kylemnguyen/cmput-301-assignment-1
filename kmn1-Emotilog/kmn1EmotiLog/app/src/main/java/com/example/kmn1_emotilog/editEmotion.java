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

public class editEmotion extends DialogFragment {



    interface EditEmotionDialogListener {
        void editEmotion(Emotion emotion);
    }
    private EditEmotionDialogListener listener;

    private Emotion selectedEmotion;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditEmotionDialogListener) {
            listener = (EditEmotionDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditEmotionDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_emoji, null);


        if(getArguments() != null) {
            selectedEmotion = (Emotion) getArguments().getSerializable("Emotion");
        }





        EditText editEmoji = view.findViewById(R.id.edit_text_emotion);
        EditText editDesc = view.findViewById(R.id.edit_text_emotion_description);

        editEmoji.setText(selectedEmotion.getEmoji());
        editDesc.setText(selectedEmotion.getDescription());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


        return builder
                .setView(view)
                .setTitle("Edit Emotion")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Change", (dialog, which) -> {



                    selectedEmotion.setEmoji(editEmoji.getText().toString());
                    selectedEmotion.setDescription(editDesc.getText().toString());

                    listener.editEmotion(selectedEmotion);


                }).create();
    }



}