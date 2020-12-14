package com.example.hw2_war_316492644.Controllers;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_war_316492644.Activities.Top10Activity;
import com.example.hw2_war_316492644.Fragments.MapsFragment;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.MyHelper;

public class Top10ViewController {// Top10 Activity Controller Class

    // Variables
    private Context context;
    private MapsFragment mapsFragment;

    private Button top10_BTN_back, top10_BTN_clearMap;

    public Top10ViewController(AppCompatActivity context, MapsFragment mapsFragment) {
        this.context = context;
        this.mapsFragment = mapsFragment;
        findViews();
        initViews();
    }

    private void initViews() {
        top10_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Top10Activity) context).finish();
            } // Exits activity
        });
        top10_BTN_clearMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHelper.getInstance().playAudio(R.raw.clear_map);
                mapsFragment.clearMarkers(); // Clears google maps from its markers
            }
        });
    }


    private void findViews() {
        top10_BTN_back = ((Top10Activity) context).findViewById(R.id.top10_BTN_back);
        top10_BTN_clearMap = ((Top10Activity) context).findViewById(R.id.top10_BTN_clearMap);
    }
}
