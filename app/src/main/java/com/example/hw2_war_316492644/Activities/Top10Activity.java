package com.example.hw2_war_316492644.Activities;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.hw2_war_316492644.Controllers.Top10ViewController;
//import com.example.hw2_war_316492644.Fragments.Fragment_Map;
import com.example.hw2_war_316492644.Fragments.Top10Fragment;
import com.example.hw2_war_316492644.Fragments.MapsFragment;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.CallBack;

public class Top10Activity extends Activity_Base {

    // Variables
    private Top10ViewController top10ViewController;

    private MapsFragment mapsFragment;
    private Top10Fragment top10ListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        isDoubleBackPressToClose = true;


        top10ViewController = new Top10ViewController(this);

        mapsFragment = new MapsFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.top10_LAY_map, mapsFragment).commit();

        top10ListFragment = new Top10Fragment();
        top10ListFragment.setCallBack(callBack);

        getSupportFragmentManager().beginTransaction().add(R.id.top10_LAY_list, top10ListFragment).commit();



    }

    private CallBack callBack = new CallBack() {

        @Override
        public void displayLocation(double lon, double lat) {
            mapsFragment.showLocationOnMap(lon, lat);
        }
    };



}