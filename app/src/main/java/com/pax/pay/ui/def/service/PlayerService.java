package com.pax.pay.ui.def.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.paxus.utils.log.Logger;

/**
 * Created by Yanina.Yang on 6/1/2020.
 */
public class PlayerService extends Service {
    public static final String PARAM_RESOURCE_ID = "resourceId";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForeground(1, new Notification());
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int resourcesId = 0;
        if(intent != null){
            resourcesId = intent.getIntExtra(PARAM_RESOURCE_ID,0);
        }
        if(resourcesId != 0) {
            Logger.i("playSound start");
            if(mediaPlayer != null){
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(this, resourcesId);
            if (mediaPlayer != null) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mp -> {
                    Logger.i("onCompletion");
                    mp.release();
                    mediaPlayer = null;
                });
                Logger.i("playSound end");
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
