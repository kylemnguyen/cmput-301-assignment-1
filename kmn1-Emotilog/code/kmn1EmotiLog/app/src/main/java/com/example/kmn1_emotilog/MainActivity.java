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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity implements  addEmojiFragment.AddEmotionDialogListener, editEmotion.EditEmotionDialogListener{


//     Global variables set up
     private ArrayList<Emotion> dataList;
     private GridView emotionList;
     private EmotionArrayAdapter emotionAdapter;


//     Create calendar
     private ArrayList<CurrentDate> calendarList;
     private GridView weekCalendar;
     private DateArrayAdapter weekCalendarAdapter;

     private UserLog summary;

     private String currentDay;

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

//        Preset Emojis

        String[] emoji = { ":)", ":|", ":(" , ">:(", "8=D", ":3"};
        String[] description = { "Happy", "Meh", "Sad", "Angry", "penit", "meow"};


//Create a new users log & set up title
        summary = new UserLog();
        TextView title = findViewById(R.id.title_text);


//        vvv Load all emotions into the grid view vvv

        dataList = new ArrayList<Emotion>();
        for (int i = 0; i < emoji.length; i++) {
            dataList.add(new Emotion(emoji[i], description[i]));
        }
        emotionList = findViewById(R.id.emotion_list);
        emotionAdapter = new EmotionArrayAdapter(this, dataList);
        emotionList.setAdapter(emotionAdapter);

//        vvv get the current date and set the date from the present and 6 days before

        calendarList = new ArrayList<CurrentDate>();
        summary.setCurrentDay(); // sets the current day

        currentDay = summary.getCurrentDay();
        String[] formattedDate = currentDay.split("-"); // Splits it up form its format of dd-MM-YYYY
        int dayOf = Integer.parseInt(formattedDate[0]);


        for (int i = -6; i <= 0; i++) { // This sets days from 6 days prior to present and adds them to the ArrayList

            String tempDate = String.valueOf(dayOf + i);
            tempDate = tempDate + "-" + formattedDate[1];

            calendarList.add(new CurrentDate(tempDate, formattedDate[2]));
        }

        weekCalendar = findViewById(R.id.calendar_of_week);
        weekCalendarAdapter = new DateArrayAdapter(this, calendarList);
        weekCalendar.setAdapter(weekCalendarAdapter);


//        Listener that sets the date for user
        weekCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CurrentDate selectedDate = (CurrentDate) parent.getItemAtPosition(position);
                summary.setDate(selectedDate.toString());
            }
        });



//        vvv Add emoji functionality

        ExtendedFloatingActionButton fab = findViewById(R.id.button_add_emotion);
        fab.setOnClickListener(v -> {
            if (dataList.size() < 9) {
                new addEmojiFragment().show(getSupportFragmentManager(), "Add City");
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "You have too many emotions available!", Toast.LENGTH_SHORT);
                toast.show();

            }

        });


//        Edit emoji functionality

        ExtendedFloatingActionButton editFab = findViewById(R.id.button_edit_emotion);
        editFab.setOnClickListener(v -> {

            editable = Boolean.TRUE;

            Toast toast = Toast.makeText(getApplicationContext(), "Selected an Emotion to edit.", Toast.LENGTH_LONG);
            toast.show();

        });

//    vvvv    Emoticon Functionality fo logging into summary and editability vvv

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
                    LoggedEmotion logE = new LoggedEmotion(selectedEmotion, summary.getCurrentDay());

                    summary.addEmotionToLog(logE);
                    System.out.println(logE.toString() + " Added to summary");

                }

            }
        });


//        Summary functionality and setup (i.e connecting variables to assets)

        ExtendedFloatingActionButton goBackBtn = findViewById(R.id.button_go_back);
        ExtendedFloatingActionButton userSummary = findViewById(R.id.daily_summary);
        ExtendedFloatingActionButton indepthSummary = findViewById(R.id.button_indepth_summary);
        TextView dailySummaryText = findViewById(R.id.daily_summary_text);


//        Frequency Functionality vvv
        userSummary.setOnClickListener(v -> {

//            Clear up display

            fab.setVisibility(View.GONE);
            editFab.setVisibility(View.GONE);
            emotionList.setVisibility(View.GONE);
            userSummary.setVisibility(View.GONE);
            weekCalendar.setVisibility(View.GONE);

//    Get summary

            if(!summary.getSummary().isEmpty()){
                dailySummaryText.setText(summary.getSummary());

            } else {
                dailySummaryText.setText("No emotions have been logged today");
            }

            dailySummaryText.setVisibility(VISIBLE);
            goBackBtn.setVisibility(VISIBLE);
            indepthSummary.setVisibility(VISIBLE);

            title.setText("Emotion Frequency " + summary.getCurrentDay());



        });

//        Go back to main buttons functionality vvvv

        goBackBtn.setOnClickListener(v -> {
//            Make assets visible
            fab.setVisibility(VISIBLE);
            editFab.setVisibility(VISIBLE);
            emotionList.setVisibility(VISIBLE);
            userSummary.setVisibility(VISIBLE);
            weekCalendar.setVisibility(VISIBLE);

//            Hide summary extra

            dailySummaryText.setVisibility(View.GONE);
            goBackBtn.setVisibility(View.GONE);
            indepthSummary.setVisibility(View.GONE);

            title.setText("How are you feeling?");


        });

//        vv Indepth summary functionality vv

        indepthSummary.setOnClickListener(v -> {

            userSummary.setVisibility(VISIBLE);
            indepthSummary.setVisibility(View.GONE);

            if(!summary.getSummaryIndepth().isEmpty()){
                dailySummaryText.setText(summary.getSummaryIndepth());

            } else {
                dailySummaryText.setText("No emotions have been logged today.");
            }

            title.setText("Indepth Event Log");

        });

    }

    //v edit the emojis fragment
     public static editEmotion newInstance(Emotion emotion) {
         Bundle args = new Bundle();
         args.putSerializable("Emotion", emotion);
         editEmotion fragment = new editEmotion();
         fragment.setArguments(args);
         return fragment;
     }
}