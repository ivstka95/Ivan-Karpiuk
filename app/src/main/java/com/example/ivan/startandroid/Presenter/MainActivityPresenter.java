package com.example.ivan.startandroid.Presenter;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.example.ivan.startandroid.View.MainActivityView;

import static android.provider.UserDictionary.Words.FREQUENCY;
import static com.example.ivan.startandroid.Presenter.Constants.LIGHT;
import static com.example.ivan.startandroid.Presenter.Constants.SOUND;
import static com.example.ivan.startandroid.Presenter.Constants.VIBRATON;

/**
 * Created by Ivan on 02.04.2017.
 */
@InjectViewState
public class MainActivityPresenter extends BasePresenter<MainActivityView> {
    private boolean serviceIsRunning;
    private ContextWrapper contextWrapper;
    private Context context;
    private boolean soundIsChecked;
    private boolean lightIsChecked;
    private boolean vibrationIsChecked;
    private int frequency;

    public void bind(Context context) {
        this.context = context;
        contextWrapper = new ContextWrapper(context);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void onSoundClicked(boolean checked) {
        this.soundIsChecked = checked;
        if (serviceIsRunning) {
            updateService(SOUND);
        }
    }

    public void onLightClicked(boolean checked) {
        this.lightIsChecked = checked;
        if (serviceIsRunning) {
            updateService(LIGHT);
        }
    }

    public void onVibrationClicked(boolean checked) {
        this.vibrationIsChecked = checked;
        if (serviceIsRunning) {
            updateService(VIBRATON);
        }
    }

    public void onFrequencyChanged(int frequency) {
        this.frequency = frequency;
        if (serviceIsRunning) {
            updateService(FREQUENCY);
        }
    }

    public void updateService(String feture) {
        contextWrapper.stopService(new Intent(context, MyService.class));
        Intent intent = new Intent(context, MyService.class);
        if (feture.equals(FREQUENCY))
            intent.putExtra(FREQUENCY, frequency);
        if (feture.equals(SOUND))
            intent.putExtra(SOUND, soundIsChecked);
        if (feture.equals(LIGHT))
            intent.putExtra(LIGHT, lightIsChecked);
        if (feture.equals(VIBRATON))
            intent.putExtra(VIBRATON, vibrationIsChecked);
        contextWrapper.startService(intent);
    }

    public void onStartClicked() {
        serviceIsRunning = true;
        contextWrapper.stopService(new Intent(context, MyService.class));
        getViewState().showProgressBar(View.VISIBLE);
        Intent intent = new Intent(context, MyService.class);
        intent.putExtra(FREQUENCY, frequency);
        intent.putExtra(SOUND, soundIsChecked);
        intent.putExtra(LIGHT, lightIsChecked);
        intent.putExtra(VIBRATON, vibrationIsChecked);
        contextWrapper.startService(intent);
    }

    public void onStopClicked(){
        getViewState().showProgressBar(View.INVISIBLE);
        serviceIsRunning = false;
        contextWrapper.stopService(new Intent(context, MyService.class));
    }
}
