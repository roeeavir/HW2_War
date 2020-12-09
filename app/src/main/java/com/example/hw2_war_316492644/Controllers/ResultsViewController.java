package com.example.hw2_war_316492644.Controllers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.hw2_war_316492644.Activities.ResultsActivity;
import com.example.hw2_war_316492644.Models.PlayerRecord;
import com.example.hw2_war_316492644.R;
import com.example.hw2_war_316492644.Utils.MyHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Random;

public class ResultsViewController {

    // Variables
    private static final int REQUEST_CODE = 101;
    private Context context;

    private TextView results_LBL_winner;
    private Button results_BTN_exit, results_BTN_addPlayerRecord;
    private EditText results_EDT_winnerName;

    private String winnerScore;


    PlayerRecord playerRecord;
    Random r;

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
    }

    private void AddPlayerRecordToTop10() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        fetchLocationAndUpdatePlayerRecords();
        results_BTN_addPlayerRecord.setEnabled(false);
    }

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
                    currentLocation = location;
                    playerRecord = new PlayerRecord(results_EDT_winnerName.getText().toString()
                            , Integer.parseInt(winnerScore), currentLocation.getLatitude(), currentLocation.getLatitude());
                    MyHelper.getInstance().addToTop10List(playerRecord);
                    Toast.makeText(context.getApplicationContext(),
                            "Latitude: " + currentLocation.getLatitude() + "\nLongitude: " +
                                    currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findViews() {
        results_LBL_winner = ((ResultsActivity) context).findViewById(R.id.results_LBL_winner);
        results_BTN_exit = ((ResultsActivity) context).findViewById(R.id.results_BTN_exit);
        results_EDT_winnerName = ((ResultsActivity) context).findViewById(R.id.results_EDT_winnerName);
        results_BTN_addPlayerRecord = ((ResultsActivity) context).findViewById(R.id.results_BTN_addPlayerRecord);
        r = new Random();
    }

    public void changeColor() {
        int color = Color.argb(255, r.nextInt(256),
                r.nextInt(256), r.nextInt(256));
        results_LBL_winner.setTextColor(color);
        results_BTN_exit.setBackgroundColor(color);
    }

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
