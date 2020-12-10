package com.example.hw2_war_316492644.Controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hw2_war_316492644.Activities.GameActivity;
import com.example.hw2_war_316492644.Activities.MainActivity;
import com.example.hw2_war_316492644.Activities.Top10Activity;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.MyHelper;

public class MainViewController { // Main Activity Controller Class

    // Variables
    private Context context;

    private ImageView main_IMG_background;
    private Button main_BTN_play, main_BTN_top10, main_BTN_exit;


    public MainViewController(AppCompatActivity context) {
        this.context = context;
        findViews();
        initViews();
    }

    private void initViews() {
        main_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toGame();
            }
        });

        main_BTN_top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTop10();
            }
        });

        main_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).finish();// Exits this activity (exits app)
            }
        });
    }

    private void toTop10() {
        main_BTN_play.setEnabled(false);// Prevents results activity to open more than once
        Intent myIntent = new Intent(context, Top10Activity.class);
        context.startActivity(myIntent);// Opens top10 activity
        main_BTN_play.setEnabled(true);// Prevents results activity to open more than once
    }

    private void toGame() {
        main_BTN_top10.setEnabled(false);// Prevents results activity to open more than once
        MyHelper.getInstance().playAudio(R.raw.start_bell);
        Intent myIntent = new Intent(context, GameActivity.class);
        context.startActivity(myIntent);// Opens game activity
        main_BTN_top10.setEnabled(true);// Prevents results activity to open more than once
    }


    private void findViews() {// Initializes views
        main_BTN_play = ((MainActivity) context).findViewById(R.id.main_BTN_play);
        main_BTN_top10 = ((MainActivity) context).findViewById(R.id.main_BTN_top10);
        main_BTN_exit = ((MainActivity) context).findViewById(R.id.main_BTN_exit);
        main_IMG_background = ((MainActivity) context).findViewById(R.id.game_IMG_background);
    }


    // Views update method
    public void updateMain_IMG_background(int id) {
        Glide.with(context).load(id).into(main_IMG_background);
    }


}
