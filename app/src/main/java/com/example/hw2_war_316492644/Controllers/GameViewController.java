package com.example.hw2_war_316492644.Controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hw2_war_316492644.Activities.GameActivity;
import com.example.hw2_war_316492644.Activities.ResultsActivity;
import com.example.hw2_war_316492644.Models.WarCardGame;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.MyHelper;

import java.util.Timer;
import java.util.TimerTask;

public class GameViewController { // Main Activity Controller Class

    // Variables
    private final int DELAY = 500;
    private final String RIGHT = "Right";
    private final String LEFT = "Left";

    private int progressStatus = 0;
    private Context context;

    private TextView game_LBL_leftScore, game_LBL_rightScore, game_LBL_center;
    private ImageView game_IMG_leftCard, game_IMG_rightCard, game_IMG_background,
            game_IMG_leftPlayer, game_IMG_rightPlayer;
    private ImageButton game_BTN_centerPlay;
    private ProgressBar progressBar;



    WarCardGame game;

    public GameViewController(AppCompatActivity context) {
        this.context = context;
        findViews();
        initViews();

        game = new WarCardGame(0, 0);

    }

    private void initViews() {
        game_BTN_centerPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press();
            }
        });
    }

    private void press() {
        game_BTN_centerPlay.setEnabled(false);
        // Changes ImageButton image to play_button
        MyHelper.getInstance().vibrate();
        if (!game.getDeck().isEmpty())
            game_BTN_centerPlay.setImageResource(context.getResources().getIdentifier(
                    "sand_clock", "drawable", context.getPackageName()));
        // If the deck is empty - game is over and we announce the winner
        if (game.getDeck().isEmpty())
            winner();
        else
            startCounting();// Play game round
    }


    private void play() {
        game.playTurn();// Updates turn_info
        String str;
        String[] turn_info = game.getTurn_info();// Needed turn info - cards id & round winner

        int left_Drawable_ID = context.getResources().getIdentifier(turn_info[0],
                "drawable", context.getPackageName());// Gets card id from card object
        int right_Drawable_ID = context.getResources().getIdentifier(turn_info[1],
                "drawable", context.getPackageName());// Gets card id from card object

        game_IMG_leftCard.setImageResource(left_Drawable_ID); // Sets images
        game_IMG_rightCard.setImageResource(right_Drawable_ID);

        if (turn_info[2].equals(RIGHT)) {// Checks and updates round winner
            updateGame_LBL_rightScore("" + game.getRightScore());
            turn_info[2] += " +1";
        } else if (turn_info[2].equals(LEFT)) {
            updateGame_LBL_leftScore("" + game.getLeftScore());
            turn_info[2] += " +1";
        }

        // Number of turns remaining and round winner
        str = "Turns left:\t\t" + game.getDeck().size() / 2 + "\n" + turn_info[2];
        updateGame_LBL_center(str);

        if (game.getDeck().isEmpty()) {// Updates ImageView to finish line
            game_BTN_centerPlay.setImageResource(context.getResources().getIdentifier(
                    "finish_line", "drawable", context.getPackageName()));
            game_BTN_centerPlay.setEnabled(true);
        }

    }

    private void winner() {
        game_BTN_centerPlay.setEnabled(false);// Prevents results activity to open more than once
        String[] strArr = game.getWinner();// Gets winner's name
        Intent myIntent = new Intent(context, ResultsActivity.class);
        myIntent.putExtra(ResultsActivity.RESULT_WINNER_NAME, strArr[0]);
        myIntent.putExtra(ResultsActivity.RESULT_WINNER_SCORE, strArr[1]);
        context.startActivity(myIntent);// Opens winner activity
        ((GameActivity) context).finish();// Exits this activity (exits app)
    }


    private void findViews() {// Initializes views
        game_LBL_leftScore = ((GameActivity) context).findViewById(R.id.game_LBL_leftScore);
        game_LBL_rightScore = ((GameActivity) context).findViewById(R.id.game_LBL_rightScore);
        game_IMG_leftCard = ((GameActivity) context).findViewById(R.id.game_IMG_leftCard);
        game_IMG_rightCard = ((GameActivity) context).findViewById(R.id.game_IMG_rightCard);
        game_BTN_centerPlay = ((GameActivity) context).findViewById(R.id.game_BTN_centerPlay);
        game_LBL_center = ((GameActivity) context).findViewById(R.id.game_LBL_center);
        game_IMG_background = ((GameActivity) context).findViewById(R.id.game_IMG_background);
        game_IMG_leftPlayer = ((GameActivity) context).findViewById(R.id.game_IMG_leftPlayer);
        game_IMG_rightPlayer = ((GameActivity) context).findViewById(R.id.game_IMG_rightPlayer);
        progressBar = ((GameActivity) context).findViewById((R.id.game_PRB_center));
    }

    private Timer carousalTimer;

    private void startCounting() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progressStatus++;
                ((GameActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!game.getDeck().isEmpty()){
                            progressBar.setProgress(progressStatus);
                            MyHelper.getInstance().playAudio(R.raw.turn_click);
                            play();
                        }else
                            carousalTimer.cancel();
                    }
                });
            }
        }, 0, DELAY);
    }

    public void stopCounting() {
        carousalTimer.cancel();
    }


    public void updateGame_LBL_center(String str) {
        game_LBL_center.setText(str);
    }

    public void updateGame_LBL_leftScore(String str) {
        game_LBL_leftScore.setText(str);
    }

    public void updateGame_LBL_rightScore(String str) {
        game_LBL_rightScore.setText(str);
    }

    public void updateGame_IMG_background(int id) {
        Glide.with(context).load(id).into(game_IMG_background);
    }

    public void updateGame_IMG_leftCard(int id) {
        Glide.with(context).load(id).into(game_IMG_leftCard);
    }

    public void updateGame_IMG_rightCard(int id) {
        Glide.with(context).load(id).into(game_IMG_rightCard);
    }

    public void updateGame_BTN_centerPlay(int id) {
        Glide.with(context).load(id).into(game_BTN_centerPlay);
    }

    public void updateGame_IMG_leftPlayer(int id) {
        Glide.with(context).load(id).into(game_IMG_leftPlayer);
    }

    public void updateGame_IMG_rightPlayer(int id) {
        Glide.with(context).load(id).into(game_IMG_rightPlayer);
    }



    public void enableButton(){
        game_BTN_centerPlay.setEnabled(true);// Allows to restart the timer after resume
        if(!game.getDeck().isEmpty())
            updateGame_BTN_centerPlay(R.drawable.start);
    }

    public void setProgressBar(){
        progressBar.setScaleY(3f);
        progressBar.setMax(game.getFullDeckSize()/2);
    }

}
