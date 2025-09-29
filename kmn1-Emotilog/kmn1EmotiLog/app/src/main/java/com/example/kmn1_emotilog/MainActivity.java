 package com.example.kmn1_emotilog;

import static android.view.View.VISIBLE;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity implements  addEmojiFragment.AddEmotionDialogListener, editEmotion.EditEmotionDialogListener{

     private ArrayList<Emotion> dataList;
     private GridView emotionList;
     private EmotionArrayAdapter emotionAdapter;
     private UserLog summary;

     private Boolean editable = Boolean.FALSE;
//     @Override
     public void addEmotion(Emotion emotion) {
         emotionAdapter.add(emotion);
         emotionAdapter.notifyDataSetChanged();
     }
     @Override
     public void editEmotion(Emotion emotion) {
         emotionAdapter.notifyDataSetChanged();
     }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] emoji = { ":)", ":|", ":(" , ">:(", "8=D", ":3"};
        String[] description = { "Happy", "Meh", "Sad", "Angry", "penit", "meow"};

        summary = new UserLog();

        dataList = new ArrayList<Emotion>();
        for (int i = 0; i < emoji.length; i++) {
            dataList.add(new Emotion(emoji[i], description[i]));
        }
        emotionList = findViewById(R.id.emotion_list);
        emotionAdapter = new EmotionArrayAdapter(this, dataList);
        emotionList.setAdapter(emotionAdapter);

        ExtendedFloatingActionButton fab = findViewById(R.id.button_add_emotion);
        fab.setOnClickListener(v -> {
            if (dataList.size() < 9) {
                new addEmojiFragment().show(getSupportFragmentManager(), "Add City");
            }
            else {

                Toast toast = Toast.makeText(getApplicationContext(), "You have too many emotions available!", Toast.LENGTH_SHORT);
                toast.show();

//                Create popup for "Too many emotions going on at the moment"
            }

        });
        ExtendedFloatingActionButton editFab = findViewById(R.id.button_edit_emotion);
        editFab.setOnClickListener(v -> {

            editable = Boolean.TRUE;

            Toast toast = Toast.makeText(getApplicationContext(), "Selected an Emotion to edit.", Toast.LENGTH_LONG);
            toast.show();

        });

        emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(editable) {
                    Emotion selectedEmotion = (Emotion) parent.getItemAtPosition(position);
                    editEmotion editEmoji = newInstance(selectedEmotion);
                    editEmoji.show(getSupportFragmentManager(), "Edit Emotion");
                    editable = Boolean.FALSE;
                }
                else {
                    Emotion selectedEmotion = (Emotion) parent.getItemAtPosition(position);
                    LoggedEmotion logE = new LoggedEmotion(selectedEmotion);

                    summary.addEmotionToLog(logE);
                    System.out.println(logE.toString() + "Added to summary");

                }


            }
        });

        ExtendedFloatingActionButton goBackBtn = findViewById(R.id.button_go_back);
        ExtendedFloatingActionButton userSummary = findViewById(R.id.daily_summary);
        ExtendedFloatingActionButton indepthSummary = findViewById(R.id.button_indepth_summary);
        TextView dailySummaryText = findViewById(R.id.daily_summary_text);

        userSummary.setOnClickListener(v -> {

//            summary.showSummary();
            fab.setVisibility(View.GONE);
            editFab.setVisibility(View.GONE);
            emotionList.setVisibility(View.GONE);
            userSummary.setVisibility(View.GONE);


//            System.out.println(summary.getSummary());
            if(!summary.getSummary().isEmpty()){
                dailySummaryText.setText(summary.getSummary());

            } else {
                dailySummaryText.setText("No emotions have been logged today.");
            }

            dailySummaryText.setVisibility(VISIBLE);
            goBackBtn.setVisibility(VISIBLE);
            indepthSummary.setVisibility(VISIBLE);


        });

        goBackBtn.setOnClickListener(v -> {
            fab.setVisibility(VISIBLE);
            editFab.setVisibility(VISIBLE);
            emotionList.setVisibility(VISIBLE);
            userSummary.setVisibility(VISIBLE);

            dailySummaryText.setVisibility(View.GONE);
            goBackBtn.setVisibility(View.GONE);
            indepthSummary.setVisibility(View.GONE);

        });

        indepthSummary.setOnClickListener(v -> {
            if(!summary.getSummaryIndepth().isEmpty()){
                dailySummaryText.setText(summary.getSummaryIndepth());

            } else {
                dailySummaryText.setText("No emotions have been logged today.");
            }
        });


//        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Emoji selectedCity = (City) adapterView.getItemAtPosition(i);
//                EditFragment editCity = newInstance(selectedCity);
//                editCity.show(getSupportFragmentManager(), "Edit City");
//
//            }
//        });
    }
     public static editEmotion newInstance(Emotion emotion) {
         Bundle args = new Bundle();
         args.putSerializable("Emotion", emotion);
         editEmotion fragment = new editEmotion();
         fragment.setArguments(args);
         return fragment;
     }
}