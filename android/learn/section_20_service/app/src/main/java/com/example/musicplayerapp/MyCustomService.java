package com.example.musicplayerapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyCustomService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(
                this,
                Settings.System.DEFAULT_RINGTONE_URI
        );


        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
