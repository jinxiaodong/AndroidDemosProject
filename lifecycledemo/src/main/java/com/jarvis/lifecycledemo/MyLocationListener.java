package com.jarvis.lifecycledemo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import javax.security.auth.callback.Callback;

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/7/26
 */
public class MyLocationListener {

    public MyLocationListener(Context context, LocationCallback callback) {

    }

    void start() {
        Log.i("MyLocationListener:", "start()");
    }


    void stop() {
        Log.i("MyLocationListener:", "stop()");
    }
}
