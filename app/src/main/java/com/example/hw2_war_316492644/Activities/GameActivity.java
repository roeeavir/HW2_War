package com.example.hw2_war_316492644.Activities;

import android.os.Bundle;
import android.util.Log;

import com.example.hw2_war_316492644.Controllers.GameViewController;
import com.example.hw2_war_316492644.R;

public class GameActivity extends Activity_Base {

    // Variables
    private GameViewController gameViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        isDoubleBackPressToClose = true;

        gameViewController = new GameViewController(this);
        gameViewController.updateGame_LBL_center("Game of War\nPress Start");
        gameViewController.updateGame_IMG_background(R.drawable.background_pokewar);
        gameViewController.updateGame_IMG_leftCard(R.drawable.poker_deck);
        gameViewController.updateGame_IMG_rightCard(R.drawable.poker_deck);
        gameViewController.updateGame_IMG_leftPlayer(R.drawable.pikachu);
        gameViewController.updateGame_IMG_rightPlayer(R.drawable.pokemon_trainer);
        gameViewController.updateGame_BTN_centerPlay(R.drawable.start);
        gameViewController.setProgressBar();

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
        gameViewController.enableButton();
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


}