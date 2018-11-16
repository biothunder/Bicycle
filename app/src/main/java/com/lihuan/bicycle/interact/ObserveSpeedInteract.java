package com.lihuan.bicycle.interact;

import com.lihuan.bicycle.repository.BicycleRepositoryType;

import org.json.JSONException;

import java.io.IOException;

import io.reactivex.Observable;

public class ObserveSpeedInteract {
    private final BicycleRepositoryType bicycleRepository;

    public ObserveSpeedInteract(BicycleRepositoryType bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    public Observable<Float> observe() {
        return bicycleRepository.observeSpeed();
    }
}
