package com.example.hw2_war_316492644.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;

import com.example.hw2_war_316492644.Controllers.ResultsViewController;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.ScreenUtils;

import java.util.Timer;
import java.util.TimerTask;

public class ResultsActivity extends Activity_Base {

    private final int DELAY = 300;
    public static final String RESULT_WINNER_NAME = "RESULT_WINNER_NAME";
    public static final String RESULT_WINNER_SCORE = "RESULT_WINNER_SCORE";

    private ResultsViewController resultsViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        isDoubleBackPressToClose = true;

        Log.d("pttt", "onCreateView - ResultsActivity");


        String winner = getIntent().getStringExtra(RESULT_WINNER_NAME);
        String score = getIntent().getStringExtra(RESULT_WINNER_SCORE);

        resultsViewController = new ResultsViewController(this);

        resultsViewController.setWinnerScore(score);
        resultsViewController.updateResults_LBL_winner(winner);
        resultsViewController.updateResults_EDT_winnerName(winner);

    }


    @Override
    protected void onStart() {
        super.onStart();
        startCounting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCounting();
    }

    private Timer carousalTimer;

    private void startCounting() {
        carousalTimer = new Timer();
        Log.d("pttt", "Starting carousal timer");
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultsViewController.changeColor();
                    }
                });
            }
        }, 0, DELAY);
    }

    private void stopCounting() {
        Log.d("pttt", "Stopping carousal timer");
        carousalTimer.cancel();
    }

}