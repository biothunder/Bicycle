package com.lihuan.bicycle.di;

import android.content.Context;

import com.lihuan.bicycle.repository.BicycleRepository;
import com.lihuan.bicycle.repository.BicycleRepositoryType;
import com.lihuan.bicycle.service.ReadDataService;
import com.lihuan.bicycle.service.ReadJsonDataService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {
    @Singleton
    @Provides
    BicycleRepositoryType provideBicycleRepository(ReadDataService readDataService) {
        return new BicycleRepository(readDataService);
    }

    @Singleton
    @Provides
    ReadDataService provideReadDataService(Context context) {
        return new ReadJsonDataService(context);
    }
}
