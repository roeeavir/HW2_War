package com.example.hw2_war_316492644.Controllers;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hw2_war_316492644.Models.PlayerRecord;
import com.example.hw2_war_316492644.R;

import java.util.Random;

public class ResultsViewController {

    // Variables

    private AppCompatActivity activity;

    private TextView results_LBL_winner;
    private Button results_BTN_exit, results_BTN_addPlayerRecord;
    private EditText results_EDT_winnerName;

    MediaPlayer mp;

    PlayerRecord playerRecord;
    Random r;

    private String winnerScore;

    public ResultsViewController(AppCompatActivity activity) {
        this.activity = activity;

        findViews();
        initViews();

        playSound(R.raw.winning);
    }

    private void initViews() {
        results_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();// Exits activity
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
        results_LBL_winner = activity.findViewById(R.id.results_LBL_winner);
        results_BTN_exit = activity.findViewById(R.id.results_BTN_exit);
        results_EDT_winnerName = activity.findViewById(R.id.results_EDT_winnerName);
        results_BTN_addPlayerRecord = activity.findViewById(R.id.results_BTN_addPlayerRecord);
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

    private void playSound(int rawId) {
        if (mp != null && mp.isPlaying()) {
            mp.reset();
            mp.release();
            mp = null;
        }

        mp = MediaPlayer.create(activity, rawId);
        mp.start();
    }


}
