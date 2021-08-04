package com.jarvis.livedatademo;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/4
 */
public class LiveDataBus {

    private static LiveDataBus liveDataBus = new LiveDataBus();

    private Map<String, MutableLiveData<Object>> bus;


    public static LiveDataBus getInstance() {
        return liveDataBus;
    }

    public LiveDataBus() {
        this.bus = new HashMap<>();
    }


    public synchronized <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }
}
