package com.lihuan.bicycle;

public abstract class C {
    public final static String TEST_DATA_1_NAME = "test_data_1.json";
    public final static String TEST_DATA_2_NAME = "test_data_2.json";
    public final static String TEST_DATA_EMPTY_NAME = "test_data_empty.json";

    public final static String JSON_KEY_SPEED = "speed";

    public final static String SPEED_ZERO = "SPEED_ZERO";

    public final static String STATE_STOP = "STATE_STOP";
    public final static String STATE_SPEED_UP = "STATE_SPEED_UP";
    public final static String STATE_SPEED_DOWN = "STATE_SPEED_DOWN";

    public final static float NS2S = 1.0f / 1000000000.0f;

    public interface ErrorCode {
        int UNKNOWN = 1;
    }
}
