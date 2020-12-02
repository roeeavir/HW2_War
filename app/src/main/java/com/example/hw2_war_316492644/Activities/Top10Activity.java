package com.example.hw2_war_316492644.Activities;


import android.os.Bundle;

import com.example.hw2_war_316492644.Controllers.Top10ViewController;
//import com.example.hw2_war_316492644.Fragments.Fragment_Map;
import com.example.hw2_war_316492644.R;

public class Top10Activity extends Activity_Base {

    // Variables
    private Top10ViewController top10ViewController;

//    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        isDoubleBackPressToClose = true;


        top10ViewController = new Top10ViewController(this);

//        fragment_map = new Fragment_Map();
//        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_map, fragment_map).commit();

    }



}