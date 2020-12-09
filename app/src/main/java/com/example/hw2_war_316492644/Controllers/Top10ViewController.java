package com.example.hw2_war_316492644.Controllers;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_war_316492644.Activities.Top10Activity;
import com.example.hw2_war_316492644.R;

public class Top10ViewController {

    // Variables
    private Context context;

    private Button top10_BTN_back;

    public Top10ViewController(AppCompatActivity context) {
        this.context = context;
        findViews();
        initViews();
    }

    private void initViews() {
        top10_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Top10Activity) context).finish();
            }
        });
    }

    private void findViews() {
        top10_BTN_back = ((Top10Activity) context).findViewById(R.id.top10_BTN_back);
    }
}
