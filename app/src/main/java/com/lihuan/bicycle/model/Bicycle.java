package com.lihuan.bicycle.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lihuan.bicycle.BR;

public class Bicycle extends BaseObservable {
    private volatile static Bicycle bicycle = null;
    private Float speed = 0.0f, powerAssisted = 0.0f, slope = 0.0f;

    public static Bicycle getInstance() {
        if (bicycle == null) {
            synchronized (Bicycle.class) {
                if (bicycle == null) {
                    bicycle = new Bicycle();
                }
            }
        }
        return bicycle;
    }

    @Bindable
    public Float getSpeed() { return speed; }

    @Bindable
    public Float getPowerAssisted() { return powerAssisted; }

    @Bindable
    public Float getSlope() { return slope; }

    public void setSpeed(Float speed) {
        this.speed = speed;
        notifyPropertyChanged(BR.speed);
    }

    public void setPowerAssisted(Float powerAssisted){
        this.powerAssisted = powerAssisted;
        notifyPropertyChanged(BR.powerAssisted);
    }

    public void setSlope(Float slope){
        this.slope = slope;
        notifyPropertyChanged(BR.slope);
    }
}
