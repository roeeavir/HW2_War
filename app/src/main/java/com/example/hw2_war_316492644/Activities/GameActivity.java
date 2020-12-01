package com.example.hw2_war_316492644.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.hw2_war_316492644.Controllers.GameViewController;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.ScreenUtils;

public class GameActivity extends AppCompatActivity {

    // Variables
    private GameViewController gameViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameViewController = new GameViewController(this);
        gameViewController.updateMain_LBL_center("Game of War\nPress Start");
        gameViewController.updateMain_LBL_leftScore("0");
        gameViewController.updateMain_LBL_rightScore("0");
        gameViewController.updateMain_IMG_background(R.drawable.background_pokewar);
        gameViewController.updateMain_IMG_leftCard(R.drawable.poker_deck);
        gameViewController.updateMain_IMG_rightCard(R.drawable.poker_deck);
        gameViewController.main_BTN_centerPlay(R.drawable.start);

    }


    // State functions
    @Override
    protected void onPause() {
        Log.d("pttt", "Pause");
        super.onPause();
        gameViewController.stopCounting(); //Pause timer
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
        gameViewController.stopCounting(); //Pause timer
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ScreenUtils.hideSystemUI(this);
        }

    }


}