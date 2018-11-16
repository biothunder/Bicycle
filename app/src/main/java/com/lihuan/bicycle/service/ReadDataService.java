package com.lihuan.bicycle.service;

import io.reactivex.Observable;

public interface ReadDataService {
    Observable<Float> read();
}
