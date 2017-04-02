package com.example.ivan.startandroid.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ivan.startandroid.Presenter.Constants;
import com.example.ivan.startandroid.Presenter.MainActivityPresenter;
import com.example.ivan.startandroid.Presenter.MyService;
import com.example.ivan.startandroid.R;

import static android.provider.UserDictionary.Words.FREQUENCY;

public class MainActivity extends MvpAppCompatActivity implements Constants, MainActivityView {

    private Button bStart;
    private Button bStop;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private Switch sSound;
    private Switch sVibration;
    private Switch sFlashlight;
    @InjectPresenter
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.bind(getApplicationContext());
        sSound = (Switch) findViewById(R.id.sSound);
        sSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSoundClicked(sSound.isChecked());
            }
        });
        sVibration = (Switch) findViewById(R.id.sVaibration);
        sVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onVibrationClicked(sVibration.isChecked());
            }
        });
        sFlashlight = (Switch) findViewById(R.id.sFlashlight);
        sFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLightClicked(sFlashlight.isChecked());
            }
        });
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                presenter.onFrequencyChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        bStart = (Button) findViewById(R.id.bStart);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onStartClicked();
            }
        });

        bStop = (Button) findViewById(R.id.bStop);
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               presenter.onStopClicked();
            }
        });
    }

    @Override
    public void showProgressBar(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
