package com.example.hw2_war_316492644.Activities;

import android.app.Application;

import com.example.hw2_war_316492644.Utils.MyHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MyHelper.init(this);

    }
}
