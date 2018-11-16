package com.lihuan.bicycle.repository;

import com.lihuan.bicycle.service.ReadDataService;

import io.reactivex.Observable;

public class BicycleRepository implements  BicycleRepositoryType{
    private final ReadDataService readDataService;

    public BicycleRepository(ReadDataService readDataService){
        this.readDataService = readDataService;
    }

    public Observable<Float> observeSpeed(){
        return readDataService.read();
    }
}
