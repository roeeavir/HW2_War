package com.example.hw2_war_316492644.Activities;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_war_316492644.Utils.ScreenUtils;

public class Activity_Base extends AppCompatActivity {

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ScreenUtils.hideSystemUI(this);
        }
    }

    protected boolean isDoubleBackPressToClose = false;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (isDoubleBackPressToClose) {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            }
            else {
                Toast.makeText(this, "Tap back button again to return/exit", Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
