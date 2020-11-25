package com.example.hw2_war_316492644.Controllers;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_war_316492644.R;

import java.util.Random;

public class ResultsViewController {

    // Variables

    private AppCompatActivity activity;

    RelativeLayout results_REL_background;
    private TextView results_LBL_winner;
    private Button results_BTN_exit;

    Random r;

    public ResultsViewController(AppCompatActivity activity) {
        this.activity = activity;
        findViews();
        initViews();
    }

    private void initViews() {
        results_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            } // Exits activity
        });
    }

    private void findViews() {
        results_LBL_winner = activity.findViewById(R.id.results_LBL_winner);
        results_BTN_exit = activity.findViewById(R.id.results_BTN_exit);
        results_REL_background = activity.findViewById(R.id.results_REL_background);
        r = new Random();
    }

    public void changeColor() {
        int color = Color.argb(255, r.nextInt(256),
                r.nextInt(256), r.nextInt(256));
        results_LBL_winner.setTextColor(color);
        results_BTN_exit.setBackgroundColor(color);
    }

    public void updateResults_LBL_winner(String str) {
        results_LBL_winner.setText(str + " Wins!!!");// Sets winner label
    }


}
