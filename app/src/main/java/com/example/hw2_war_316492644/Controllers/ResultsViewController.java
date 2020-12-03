package com.example.hw2_war_316492644.Controllers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hw2_war_316492644.Activities.ResultsActivity;
import com.example.hw2_war_316492644.Models.PlayerRecord;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.AudioPlayer;

import java.util.Random;

public class ResultsViewController {

    // Variables

    private Context context;

    private TextView results_LBL_winner;
    private Button results_BTN_exit, results_BTN_addPlayerRecord;
    private EditText results_EDT_winnerName;

    MediaPlayer mp;
    AudioPlayer ap;

    PlayerRecord playerRecord;
    Random r;

    private String winnerScore;

    public ResultsViewController(Context context) {
        this.context = context;

        findViews();
        initViews();

        ap.playAudio(R.raw.winning);
    }

    private void initViews() {
        results_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ResultsActivity) context).finish();// Exits activity
            }
        });

        results_BTN_addPlayerRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerRecord();
            } // Exits activity
        });
    }

    private void setPlayerRecord() {
        playerRecord = new PlayerRecord(results_EDT_winnerName.getText().toString()
                , Integer.parseInt(winnerScore));
    }

    private void findViews() {
        results_LBL_winner = ((ResultsActivity) context).findViewById(R.id.results_LBL_winner);
        results_BTN_exit = ((ResultsActivity) context).findViewById(R.id.results_BTN_exit);
        results_EDT_winnerName = ((ResultsActivity) context).findViewById(R.id.results_EDT_winnerName);
        results_BTN_addPlayerRecord = ((ResultsActivity) context).findViewById(R.id.results_BTN_addPlayerRecord);
        mp = new MediaPlayer();
        ap = new AudioPlayer(context, mp);
        r = new Random();
    }

    public void changeColor() {
        int color = Color.argb(255, r.nextInt(256),
                r.nextInt(256), r.nextInt(256));
        results_LBL_winner.setTextColor(color);
        results_BTN_exit.setBackgroundColor(color);
    }

    public void updateResults_LBL_winner(String winner) {
        String str = winner + " Wins with score of " + winnerScore;
        results_LBL_winner.setText(str);// Sets winner label
    }


    public void updateResults_EDT_winnerName(String str) {
        results_EDT_winnerName.setText(str);
    }

    public void setWinnerScore(String winnerScore) {
        this.winnerScore = winnerScore;
    }



}
