package com.example.hw2_war_316492644.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.hw2_war_316492644.Controllers.MainViewController;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.ScreenUtils;


public class MainActivity extends AppCompatActivity {

    // Variables
    private MainViewController mainViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewController = new MainViewController(this);
        mainViewController.updateMain_IMG_background(R.drawable.background_war);

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
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ScreenUtils.hideSystemUI(this);
        }

    }


}