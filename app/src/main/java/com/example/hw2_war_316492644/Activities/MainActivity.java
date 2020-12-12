package com.example.hw2_war_316492644.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.hw2_war_316492644.Controllers.MainViewController;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.ScreenUtils;


public class MainActivity extends Activity_Base {

    // Variables
    private MainViewController mainViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isDoubleBackPressToClose = true;

        Log.d("pttt", "onCreateView - MainActivity");

        mainViewController = new MainViewController(this);
        mainViewController.updateMain_IMG_background(R.drawable.background_war);

    }


}