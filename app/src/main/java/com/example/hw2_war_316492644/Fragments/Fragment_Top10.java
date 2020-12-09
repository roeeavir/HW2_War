package com.example.hw2_war_316492644.Fragments;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hw2_war_316492644.R;

public class Fragment_Top10 extends Fragment {

    private TextView list_LBL_title;
//    private Button list_BTN_changeActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_top10list, container, false);
        findViews(view);
        initViews();

        refreshList();
        return view;
    }

    private void initViews() {
//        list_BTN_changeActivity.setOnClickListener(onClickListener);
    }


    public void refreshList() {
        String date = DateFormat.format("dd.MM.yy\nHH:mm:ss", System.currentTimeMillis()).toString();
        list_LBL_title.setText(date);
    }

    private void findViews(View view) {
//        list_LBL_title = view.findViewById(R.id.list_LBL_title);
//        list_BTN_changeActivity = view.findViewById(R.id.list_BTN_changeActivity);
    }
}
