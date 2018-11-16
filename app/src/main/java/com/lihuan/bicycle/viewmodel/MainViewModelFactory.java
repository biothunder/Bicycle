package com.lihuan.bicycle.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lihuan.bicycle.interact.ObserveSpeedInteract;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;
    private final ObserveSpeedInteract observeSpeedInteract;

    public MainViewModelFactory(Context context, ObserveSpeedInteract observeSpeedInteract) {
        this.context = context;
        this.observeSpeedInteract = observeSpeedInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(context, observeSpeedInteract);
    }
}
