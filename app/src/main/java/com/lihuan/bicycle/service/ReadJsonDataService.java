package com.lihuan.bicycle.service;

import android.content.Context;
import android.util.Log;

import com.lihuan.bicycle.C;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class ReadJsonDataService implements ReadDataService {
    private final Context context;

    public ReadJsonDataService(Context context) {
        this.context = context;
    }

    public Observable<Float> read() {

        JSONArray speedJSONArray = getSpeedArray();

        /**
         * 每過600毫秒發送一次速度的數據
         */
        return Observable.interval(C.PERIOD, TimeUnit.MILLISECONDS)
                .filter(speed -> speedJSONArray != null)
                .flatMap(counter -> Observable.fromCallable(() -> {
                    float speed;
                    if (counter <= speedJSONArray.length())
                        speed = Float.valueOf(speedJSONArray.get(counter.intValue()).toString());
                     else
                        speed = Float.valueOf(-1);

                    return speed;
                }))
                .takeUntil(speed -> speed < 0);
    }

    private JSONArray getSpeedArray() {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream;
        BufferedReader bufferedReader;
        String content;
        JSONObject jsonObject;
        JSONArray speedJSONArray = null;

        try {
            inputStream = context.getAssets().open(C.TEST_DATA_2_NAME);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((content = bufferedReader.readLine()) != null) {
                stringBuilder.append(content);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            jsonObject = new JSONObject(stringBuilder.toString());
            speedJSONArray = jsonObject.getJSONArray(C.JSON_KEY_SPEED);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            return speedJSONArray;
        }
    }
}
