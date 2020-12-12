package com.example.hw2_war_316492644.Activities;

import android.app.Application;

import com.example.hw2_war_316492644.Utils.MyHelper;
// An activity that runs when the app is active and provides itself to MyHelper class
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MyHelper.init(this);

    }
}
