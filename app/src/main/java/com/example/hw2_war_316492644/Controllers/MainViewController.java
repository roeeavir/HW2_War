package com.example.hw2_war_316492644.Controllers;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hw2_war_316492644.Activities.ResultsActivity;
import com.example.hw2_war_316492644.Models.WarCardGame;
import com.example.hw2_war_316492644.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainViewController { // Main Activity Controller Class

    // Variables
    private final int DELAY = 200;
    private AppCompatActivity activity;

    private TextView main_LBL_leftScore, main_LBL_rightScore, main_LBL_center;
    private ImageView main_IMG_leftCard, main_IMG_rightCard, main_IMG_background;
    private ImageButton main_BTN_centerPlay;

    MediaPlayer mp;

    WarCardGame game;

    public MainViewController(AppCompatActivity activity) {
        this.activity = activity;
        findViews();
        initViews();
    }

    private void initViews() {
        main_BTN_centerPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn();
            }
        });
    }

    private void turn() {
        main_BTN_centerPlay.setEnabled(false);
        // Changes ImageButton image to play_button
        if (game.getDeck().size() == game.getFullDeckSize()){
            playSound(R.raw.start_bell);
            main_BTN_centerPlay.setImageResource(activity.getResources().getIdentifier(
                    "sand_clock", "drawable", activity.getPackageName()));
        }
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

        int left_Drawable_ID = activity.getResources().getIdentifier(turn_info[0],
                "drawable", activity.getPackageName());// Gets card id from card object
        int right_Drawable_ID = activity.getResources().getIdentifier(turn_info[1],
                "drawable", activity.getPackageName());// Gets card id from card object

        main_IMG_leftCard.setImageResource(left_Drawable_ID); // Sets images
        main_IMG_rightCard.setImageResource(right_Drawable_ID);

        if (turn_info[2].equals("Right")) {// Checks and updates round winner
            updateMain_LBL_rightScore("" + game.getRightScore());
            turn_info[2] += " +1";
        } else if (turn_info[2].equals("Left")) {
            updateMain_LBL_leftScore("" + game.getLeftScore());
            turn_info[2] += " +1";
        }

        // Number of turns remaining and round winner
        str = "Turns left:\t\t" + game.getDeck().size() / 2 + "\n" + turn_info[2];
        updateMain_LBL_center(str);

        if (game.getDeck().isEmpty()) {// Updates ImageView to finish line
            main_BTN_centerPlay.setImageResource(activity.getResources().getIdentifier(
                    "finish_line", "drawable", activity.getPackageName()));
            main_BTN_centerPlay.setEnabled(true);
        }



    }

    private void winner() {
        main_BTN_centerPlay.setEnabled(false);// Prevents results activity to open more than once
        String str = game.getWinner();// Gets winner's name
        Intent myIntent = new Intent(activity, ResultsActivity.class);
        myIntent.putExtra(ResultsActivity.RESULT_WINNER, str);
        activity.startActivity(myIntent);// Opens winner activity
        activity.finish();// Exits this activity (exits app)
    }


    private void findViews() {// Initializes views
        main_LBL_leftScore = activity.findViewById(R.id.main_LBL_leftScore);
        main_LBL_rightScore = activity.findViewById(R.id.main_LBL_rightScore);
        main_IMG_leftCard = activity.findViewById(R.id.main_IMG_leftCard);
        main_IMG_rightCard = activity.findViewById(R.id.main_IMG_rightCard);
        main_BTN_centerPlay = activity.findViewById(R.id.main_BTN_centerPlay);
        main_LBL_center = activity.findViewById(R.id.main_LBL_center);
        main_IMG_background = activity.findViewById(R.id.main_IMG_background);
        game = new WarCardGame(0, 0);
    }

    private Timer carousalTimer;

    private void startCounting() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!game.getDeck().isEmpty())
                            play();
                    }
                });
            }
        }, 0, DELAY);
    }

    public void stopCounting() {
        carousalTimer.cancel();
    }

    private void playSound(int rawId) {
        if (mp!=null  &&  mp.isPlaying()) {
            mp.reset();
            mp.release();
            mp = null;
        }

        mp = MediaPlayer.create(activity, rawId);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.reset();
                mp.release();
                mp=null;
            }
        });
        mp.start();
    }

    public void updateMain_LBL_center(String str) {
        main_LBL_center.setText(str);
    }

    public void updateMain_LBL_leftScore(String str) {
        main_LBL_leftScore.setText(str);
    }

    public void updateMain_LBL_rightScore(String str) {
        main_LBL_rightScore.setText(str);
    }

    public void updateMain_IMG_background(int id){
        Glide.with(activity).load(id).into(main_IMG_background);
    }

    public void updateMain_IMG_leftCard(int id){
        Glide.with(activity).load(id).into(main_IMG_leftCard);
    }

    public void updateMain_IMG_rightCard(int id){
        Glide.with(activity).load(id).into(main_IMG_rightCard);
    }

    public void main_BTN_centerPlay(int id){
        Glide.with(activity).load(id).into(main_BTN_centerPlay);
    }

}
