package com.example.hw2_war_316492644.Fragments;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hw2_war_316492644.Models.Top10List;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.CallBack;
import com.example.hw2_war_316492644.Utils.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class Top10Fragment extends Fragment {

    private ArrayAdapter<Top10List> arrayAdapter;
    private ListView top10_LSV_playerRecords;

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_top10list, container, false);
        findViews(view);
        initViews();


        top10_LSV_playerRecords.setAdapter(arrayAdapter);


        return view;
    }

    private void initViews() {
        top10_LSV_playerRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (callBack != null) {
                    callBack.displayLocation(MyHelper.getInstance().getTop10List().getTopPlayers()
                            .get(position).getCurrentLongitude(), MyHelper.getInstance()
                            .getTop10List().getTopPlayers().get(position).getCurrentLatitude());
                }
//                MyHelper.getInstance().toast(MyHelper.getInstance().getTop10List().getTopPlayers().get(position).getName());
            }
        });
    }


    private void findViews(View view) {
        top10_LSV_playerRecords = view.findViewById(R.id.top10_LSV_playerRecords);
        arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, MyHelper.getInstance().getTop10List().getTopPlayers());
    }
}
