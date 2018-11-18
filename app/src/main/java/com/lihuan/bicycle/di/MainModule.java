package com.lihuan.bicycle.di;

import android.content.Context;

import com.lihuan.bicycle.interact.ObserveSpeedInteract;
import com.lihuan.bicycle.repository.BicycleRepositoryType;
import com.lihuan.bicycle.viewmodel.MainViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    MainViewModelFactory provideMainViewModelFactory(Context context,
                                                     ObserveSpeedInteract observeSpeedInteract){
        return new MainViewModelFactory(context, observeSpeedInteract);
    }

    @Provides
    ObserveSpeedInteract provideFetchSpeedInteract(BicycleRepositoryType bicycleRepositoryType) {
        return new ObserveSpeedInteract(bicycleRepositoryType);
    }
}
