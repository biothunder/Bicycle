package com.lihuan.bicycle.error;

import android.support.annotation.Nullable;

public class ServiceErrorException extends Exception {

    /*
     * 在這邊自訂義錯誤種類
     */
    public static final int UNKNOWN_ERROR = -1;

    public final int code;

    public ServiceErrorException(int code, @Nullable String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public ServiceErrorException(int code, @Nullable String message) {
        this(code, message, null);
    }

    public ServiceErrorException(int code) {
        this(code, null);
    }
}
