package com.example.hw2_war_316492644.Utils;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.hw2_war_316492644.Models.PlayerRecord;
import com.example.hw2_war_316492644.Models.Top10List;

import java.util.ArrayList;

public class MyHelper {

    // Variables
    private final int VIBRATE_DURATION = 500;

    private static MyHelper instance;
    private Context appContext;


    private MediaPlayer mp;

    public static MyHelper getInstance() {
        return instance;
    }

    private MyHelper(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    public static void init(Context appContext) {
        if (instance == null) {
            instance = new MyHelper(appContext);
        }
    }


    public void vibrate() {
        Vibrator v = (Vibrator) appContext.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(VIBRATE_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(VIBRATE_DURATION);
        }
    }


    // Allows to play audio files
    public void playAudio(int rawId) {
        stopAudio();
        mp = MediaPlayer.create(this.appContext,rawId);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp=null;
            }
        });
        mp.start();
    }

    // Stops playing audio
    public void stopAudio() {
        if (mp != null) {
            try {
                mp.reset();
                mp.prepare();
                mp.stop();
                mp.release();
                mp=null;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void toast(String message) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
    }
}
