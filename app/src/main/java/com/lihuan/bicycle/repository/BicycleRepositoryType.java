package com.lihuan.bicycle.repository;

import io.reactivex.Observable;

public interface BicycleRepositoryType {
    Observable<Float> observeSpeed();
}
