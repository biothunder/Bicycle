package com.lihuan.bicycle.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.lihuan.bicycle.C;
import com.lihuan.bicycle.interact.ObserveSpeedInteract;

import io.reactivex.Single;

import static android.util.Half.EPSILON;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class MainViewModel extends BaseViewModel implements SensorEventListener {
    private final Context context;
    private final ObserveSpeedInteract observeSpeedInteract;

    private final MutableLiveData<Float> speed = new MutableLiveData<>();
    private final MutableLiveData<Float> powerAssisted = new MutableLiveData<>();
    private final MutableLiveData<Float> slope = new MutableLiveData<>();

    private float timestamp;
    private final float[] deltaRotationVector = new float[4];
    private float[] rotvecOrientValues = new float[3];
    private float degree = 0.0f;

    public MainViewModel(Context context, ObserveSpeedInteract observeSpeedInteract) {
        this.observeSpeedInteract = observeSpeedInteract;
        this.context = context;

        speed.postValue(0.0f);
        observeBicycleState();
    }

    private void observeBicycleState() {
        observeSpeed();
        registerGyroscopeListener();
    }

    private void observeSpeed() {
        observeSpeedInteract
                .observe()
                .subscribe(this::onSpeed, this::onError);
    }

    private void onSpeed(Float speed){
        /**
        * 先用速度之間的差值來做為馬達助力
        */
        Single.just(speed)
                .map(powerAssisted -> speed - this.speed.getValue())
                .subscribe(powerAssisted::postValue, this::onError);

        this.speed.postValue(speed);
    }

    private void registerGyroscopeListener() {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    /**
     * 根據陀螺儀數據設置傾斜高度
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        long benchmarkTime = System.nanoTime();
        if (timestamp != 0) {
            final float dT = (benchmarkTime - timestamp) * C.NS2S;

            float axisX = sensorEvent.values[0];
            float axisY = sensorEvent.values[1];
            float axisZ = sensorEvent.values[2];

            float omegaMagnitude = (float) sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

            if (omegaMagnitude > EPSILON) {
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;
            }

            float thetaOverTwo = omegaMagnitude * dT / 2.0f;
            float sinThetaOverTwo = (float) sin(thetaOverTwo);
            float cosThetaOverTwo = (float) cos(thetaOverTwo);
            deltaRotationVector[0] = sinThetaOverTwo * axisX;
            deltaRotationVector[1] = sinThetaOverTwo * axisY;
            deltaRotationVector[2] = sinThetaOverTwo * axisZ;
            deltaRotationVector[3] = cosThetaOverTwo;
        }

        timestamp = benchmarkTime;
        float[] deltaRotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
        SensorManager.getOrientation(deltaRotationMatrix, rotvecOrientValues);

        if (rotvecOrientValues != null) {
            /**
            * 使用X軸的旋轉角度和Z軸的旋轉角度做為馬達助力圖形傾斜角度的參考依據
            */
            degree += (float) Math.toDegrees(rotvecOrientValues[0] + rotvecOrientValues[2]) / 2;
            slope.postValue(degree);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public LiveData<Float> speed() {
        return speed;
    }

    public LiveData<Float> powerAssisted() {
        return powerAssisted;
    }

    public LiveData<Float> slope() {
        return slope;
    }
}



