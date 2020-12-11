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

        Log.d("pttt", "onCreateView - GameActivity");

        gameViewController = new GameViewController(this);
        gameViewController.updateGame_LBL_center("Game of War\nPress Start");
        gameViewController.updateGame_IMG_background(R.drawable.background_pokemonwar);
        gameViewController.updateGame_IMG_leftCard(R.drawable.poker_deck);
        gameViewController.updateGame_IMG_rightCard(R.drawable.poker_deck);
        gameViewController.updateGame_IMG_leftPlayer(R.drawable.pikachu);
        gameViewController.updateGame_IMG_rightPlayer(R.drawable.pokemon_trainer);
        gameViewController.updateGame_BTN_centerPlay(R.drawable.start);
        gameViewController.setProgressBar();

    }


    // State function

    @Override
    protected void onStop() {
        super.onStop();
        if (gameViewController.isTimerRunning())
            gameViewController.stopCounting(); //Pause timer
    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "Destroy");
        if (gameViewController.isTimerRunning())
            gameViewController.stopCounting(); //Stop timer
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d("pttt", "Resume and re-enable play button");
        gameViewController.enableButton();
        super.onResume();
    }

}