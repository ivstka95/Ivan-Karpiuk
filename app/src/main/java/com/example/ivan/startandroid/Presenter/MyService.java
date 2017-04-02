package com.example.ivan.startandroid.Presenter;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.ivan.startandroid.R;

import static android.provider.UserDictionary.Words.FREQUENCY;

/**
 * Created by Ivan on 30.03.2017.
 */

public class MyService extends Service implements Constants {

    private Thread thread;
    private boolean running;
    private static boolean vibrate;
    private static boolean light;
    private static boolean sound;
    private static int frequency;
    private Camera camera;
    private Camera.Parameters parameters;
    MediaPlayer mp;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        running = true;
        if (intent != null) {
            if (intent.hasExtra(VIBRATON))
                vibrate = intent.getBooleanExtra(VIBRATON, false);
            if (intent.hasExtra(LIGHT))
                light = intent.getBooleanExtra(LIGHT, false);
            if (intent.hasExtra(SOUND))
                sound = intent.getBooleanExtra(SOUND, false);
            if (intent.hasExtra(FREQUENCY))
                frequency = intent.getIntExtra(FREQUENCY, 1);
        }
        backGroundTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        getCamera();
        super.onCreate();
    }

    void backGroundTask() {
        thread = new Thread() {
            @Override
            public void run() {
                while (running) {
                    try {
                        sleep(5000 / (frequency + 1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (vibrate) {
                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                        vibrator.vibrate(500);
                    }
                    if (light)
                        turnOnFlash();
                    if (sound)
                        playSound();
                    turnOffFlash();
                    try {
                        sleep(5000 / (frequency + 1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        ;
        thread.start();
    }

    @Override
    public void onDestroy() {
        running = false;
        if (camera != null) {
            camera.release();
            camera = null;
        }
        super.onDestroy();
    }

    private void playSound() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.beep22);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mp.start();
    }

    private void turnOnFlash() {
        try {
            if (camera == null || parameters == null) {
                return;
            }
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void turnOffFlash() {
        try {
            if (camera == null || parameters == null) {
                return;
            }
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                parameters = camera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Failed to Open", e.getMessage());
            }
        }
    }
}
