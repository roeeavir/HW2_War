package com.example.hw2_war_316492644.Controllers;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.hw2_war_316492644.Activities.ResultsActivity;
import com.example.hw2_war_316492644.Models.PlayerRecord;
import com.example.hw2_war_316492644.Models.Top10List;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.MyHelper;
import com.example.hw2_war_316492644.Utils.ScreenUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.content.Context.MODE_PRIVATE;
import static com.example.hw2_war_316492644.Utils.Constants.SP_FILE;
import static com.example.hw2_war_316492644.Utils.Constants.TOP10;

import com.google.gson.Gson;


import java.util.Random;

public class ResultsViewController { // Results Activity Controller Class

    // Variables
    private static final int REQUEST_CODE = 101; // Google Maps related code
    private Context context;

    private TextView results_LBL_winner;
    private Button results_BTN_exit, results_BTN_addPlayerRecord;
    private EditText results_EDT_winnerName;

    private String winnerScore;


    PlayerRecord playerRecord;
    Top10List top10List;

    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public ResultsViewController(Context context) {
        this.context = context;

        findViews();
        initViews();

        MyHelper.getInstance().playAudio(R.raw.winning);

    }

    private void initViews() {
        results_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ResultsActivity) context).finish();// Exits activity
            }
        });

        results_BTN_addPlayerRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlayerRecordToTop10();
            }
        });
        results_EDT_winnerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            // Changes label based on the winner's new custom name
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0)
                    updateResults_LBL_winner(s.toString());
                else
                    updateResults_LBL_winner("Player");
                ScreenUtils.hideSystemUI((ResultsActivity)context); // Hides system UI
            }

            @Override
            public void afterTextChanged(Editable s) {
            }


        });

    }

    // Adds a player's record to top10 list and sends a message using Toast
    private void AddPlayerRecordToTop10() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        fetchLocationAndUpdatePlayerRecords(); // Fetching location and updating player record list
        results_BTN_addPlayerRecord.setEnabled(false); // Disabling button
        results_EDT_winnerName.setEnabled(false); // Disabling name changing
        results_EDT_winnerName.setFocusable(false); // Disabling name changing
        MyHelper.getInstance().playAudio(R.raw.player_update);

    }

    // Method for getting the current location of the user and then setting the gson/json data
    private void fetchLocationAndUpdatePlayerRecords() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((ResultsActivity) context, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d("pttt", "Fetching current location");
                    currentLocation = location;
                    playerRecord = new PlayerRecord(results_EDT_winnerName.getText().toString()
                            , Integer.parseInt(winnerScore), currentLocation.getLongitude(),
                            currentLocation.getLatitude());

                    updateTop10Data(playerRecord);
                }
            }
        });
    }

    // Loads current top10 list from file, updates it with new player record (if needed), then loads it back to file
    private void updateTop10Data(PlayerRecord playerRecord) {
        Log.d("pttt", "Top10 list data being updated");
        SharedPreferences prefs = context.getSharedPreferences(SP_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();

        top10List = generateData(prefs, gson);// Loads top10 list from file

        if (top10List == null) // If there is no top 10 list, create one
            top10List = new Top10List();

        MyHelper.getInstance().toast(top10List.addPlayerRecord(playerRecord)); // Updates top10 list

        // Loads updated top10 list into file
        String json = gson.toJson(top10List);
        editor.putString(TOP10, json);
        editor.apply();
    }

    // A method for loading a top10 list from file
    private Top10List generateData(SharedPreferences prefs, Gson gson) {
        String jsonFromMemory = prefs.getString(TOP10, "");
        return gson.fromJson(jsonFromMemory, Top10List.class);
    }


    private void findViews() {
        results_LBL_winner = ((ResultsActivity) context).findViewById(R.id.results_LBL_winner);
        results_BTN_exit = ((ResultsActivity) context).findViewById(R.id.results_BTN_exit);
        results_EDT_winnerName = ((ResultsActivity) context).findViewById(R.id.results_EDT_winnerName);
        results_BTN_addPlayerRecord = ((ResultsActivity) context).findViewById(R.id.results_BTN_addPlayerRecord);
    }

    public void changeColor() {// Changes the color of the exit button and the winner label
        Random r = new Random();
        int color = Color.argb(255, r.nextInt(256),
                r.nextInt(256), r.nextInt(256));
        results_LBL_winner.setTextColor(color);
        results_BTN_exit.setBackgroundColor(color);
    }

    // Shows the winner's name
    public void updateResults_LBL_winner(String winner) {
        String str = winner + " Wins with score of " + winnerScore;
        results_LBL_winner.setText(str);// Sets winner label
    }


    public void updateResults_EDT_winnerName(String str) {
        results_EDT_winnerName.setText(str);
    }

    public void setWinnerScore(String winnerScore) {
        this.winnerScore = winnerScore;
    }


}
