package com.example.hw2_war_316492644.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.hw2_war_316492644.Models.Top10List;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.CallBack;
import com.example.hw2_war_316492644.Utils.MyHelper;
import com.google.gson.Gson;


import static android.content.Context.MODE_PRIVATE;
import static com.example.hw2_war_316492644.Utils.Constants.SP_FILE;

public class Top10Fragment extends Fragment {

    public static final String TOP10 = "Top10";


    private ArrayAdapter<Top10List> arrayAdapter;
    private ListView top10_LSV_playerRecords;
    private Top10List top10List;

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

        SharedPreferences prefs = getActivity().getSharedPreferences(SP_FILE, MODE_PRIVATE);
        Gson gson = new Gson();

        top10List = generateData(prefs, gson);

        if (top10List != null) {
            arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, top10List.getTopPlayers());

            top10_LSV_playerRecords.setAdapter(arrayAdapter);
        }


        return view;
    }

    private void initViews() {
        top10_LSV_playerRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (callBack != null) {
                    callBack.displayLocation(top10List.getTopPlayers().get(position).
                            getCurrentLongitude(), top10List.getTopPlayers().get(position)
                            .getCurrentLatitude());
                }
            }
        });
    }


    private void findViews(View view) {
        top10_LSV_playerRecords = view.findViewById(R.id.top10_LSV_playerRecords);
    }

    private Top10List generateData(SharedPreferences prefs, Gson gson) {
        String jsonFromMemory = prefs.getString(TOP10, "");
        return gson.fromJson(jsonFromMemory, Top10List.class);
    }
}
