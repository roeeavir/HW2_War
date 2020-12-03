package com.example.hw2_war_316492644.Utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class AudioPlayer {

    // Variables
    private Context context;

    private MediaPlayer mp;

    public AudioPlayer(Context context, MediaPlayer mp) {
        this.context = context;
        this.mp = mp;
    }

    public void playAudio(int rawId) {
        stopAudio();
        Log.d("pttt", "~Playing sound~");
        mp = MediaPlayer.create(this.context,rawId);
        Log.d("pttt", "~~~~~");
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

    public void stopAudio() {
        if (mp != null) {
            Log.d("pttt", "~Stoping sound~");
            try {
                mp.reset();
                mp.prepare();
                mp.stop();
                mp.release();
                mp=null;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
