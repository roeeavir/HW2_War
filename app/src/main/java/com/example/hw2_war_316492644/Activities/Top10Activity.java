package com.example.hw2_war_316492644.Activities;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.hw2_war_316492644.Controllers.Top10ViewController;
//import com.example.hw2_war_316492644.Fragments.Fragment_Map;
import com.example.hw2_war_316492644.Fragments.MapsFragment;
import com.example.hw2_war_316492644.R;

public class Top10Activity extends Activity_Base {

    // Variables
    private Top10ViewController top10ViewController;

    private Fragment mapsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        isDoubleBackPressToClose = true;


        top10ViewController = new Top10ViewController(this);

        mapsFragment = new MapsFragment();

//        getSupportFragmentManager().beginTransaction().add(R.id.top10_LAY_map, mapsFragment).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.top10_LAY_map, mapsFragment).commit();



    }



}