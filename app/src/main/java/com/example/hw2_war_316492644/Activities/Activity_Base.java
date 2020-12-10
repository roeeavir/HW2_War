package com.example.hw2_war_316492644.Activities;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_war_316492644.Utils.ScreenUtils;

public class Activity_Base extends AppCompatActivity {

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) { // Controls the system UI to enable full screen
            Log.d("pttt", "Hiding system UI");
            ScreenUtils.hideSystemUI(this);
        }
    }

    protected boolean isDoubleBackPressToClose = false;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;


    @Override
    public void onBackPressed() {
        if (isDoubleBackPressToClose) {
            // If the back button is pressed twice in the set time window, the activity will close.
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            }
            else { // Shows a message in case the back button is pressed
                Log.d("pttt", "Tapped the back button once");
                Toast.makeText(this, "Tap back button again to return/exit", Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    // State functions
    @Override
    protected void onPause() {
        Log.d("pttt", "Pause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "Start");
        super.onStart();
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
    }
}
