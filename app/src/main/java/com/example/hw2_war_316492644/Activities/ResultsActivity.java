package com.example.hw2_war_316492644.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.example.hw2_war_316492644.Controllers.ResultsViewController;
import com.example.hw2_war_316492644.R;

import java.util.Timer;
import java.util.TimerTask;

public class ResultsActivity extends AppCompatActivity {

    private final int DELAY = 500;
    public static final String RESULT_WINNER = "RESULT_WINNER";

    private ResultsViewController resultsViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        String winner = getIntent().getStringExtra(RESULT_WINNER);
        resultsViewController = new ResultsViewController(this);

        resultsViewController.updateResults_LBL_winner(winner);
        resultsViewController.updateResults_IMG_background(R.drawable.background_peace);

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
        carousalTimer.cancel();
    }
}