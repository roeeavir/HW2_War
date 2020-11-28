package com.example.hw2_war_316492644.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hw2_war_316492644.Controllers.MainViewController;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.ScreenUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Variables
    private MainViewController mainViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewController = new MainViewController(this);
        mainViewController.updateMain_LBL_center("Game of War\nPress Start");
        mainViewController.updateMain_LBL_leftScore("0");
        mainViewController.updateMain_LBL_rightScore("0");
        mainViewController.updateMain_IMG_background(R.drawable.background_war);
        mainViewController.updateMain_IMG_leftCard(R.drawable.poker_deck);
        mainViewController.updateMain_IMG_rightCard(R.drawable.poker_deck);
        mainViewController.main_BTN_centerPlay(R.drawable.start);

    }


    // State functions
    @Override
    protected void onPause() {
        Log.d("pttt", "Pause");
        super.onPause();
        mainViewController.stopCounting(); //Pause timer
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "Start");
        super.onStart();

        Log.d("pttt", "Resume/Start Timer");
    }

    @Override
    protected void onResume() {
        Log.d("pttt", "Resume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "Destroy");
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mainViewController.stopCounting(); //Pause timer
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ScreenUtils.hideSystemUI(this);
        }

    }


}