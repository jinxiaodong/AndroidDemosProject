package com.jarvis.lifecycledemo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/7/26
 */
public class MyLocationListener2 implements LifecycleObserver {

    public MyLocationListener2(Context context, LocationCallback callback) {

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        Log.i("MyLocationListener2:", "start()");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        Log.i("MyLocationListener2:", "stop()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void any(LifecycleOwner owner) {
        Log.i("MyLocationListener2:", "any():"+owner.getLifecycle().getCurrentState());
    }

}
