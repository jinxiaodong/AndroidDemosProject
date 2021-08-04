package com.jarvis.livedatademo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/4
 */
public class LiveDataBusX {

    private static LiveDataBusX liveDataBus = new LiveDataBusX();

    private Map<String, MutableLiveDataX<Object>> bus;


    public static LiveDataBusX getInstance() {
        return liveDataBus;
    }

    public LiveDataBusX() {
        this.bus = new HashMap<>();
    }


    public synchronized <T> MutableLiveDataX<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new MutableLiveDataX<Object>());
        }
        return (MutableLiveDataX<T>) bus.get(key);
    }


    public static class MutableLiveDataX<T> extends MutableLiveData {


        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
            super.observe(owner, observer);
            hook(observer);
        }

        private void hook(Observer<? super T> observer) {

            try {
                Class<LiveData> liveDataClass = LiveData.class;

                Field mObserversField = liveDataClass.getDeclaredField("mObservers");

                mObserversField.setAccessible(true);

                Object mObserversObj = mObserversField.get(this);
                Class<?> mObserversObjClass = mObserversObj.getClass();
                Method get = mObserversObjClass.getDeclaredMethod("get", Object.class);
                get.setAccessible(true);

                Object observerEntry = get.invoke(mObserversObj, observer);
                Object observerWrapper = null;
                if (observerEntry instanceof Map.Entry) {
                    observerWrapper = ((Map.Entry) observerEntry).getValue();
                }
                if (observerWrapper == null) {
                    throw new NullPointerException("observerWrapper is null");
                }

                Class<?> observerWrapperClass = observerWrapper.getClass().getSuperclass();
                Log.i("LiveDataBusX", observerWrapperClass.getName());
                Field mLastVersion = observerWrapperClass.getDeclaredField("mLastVersion");
                mLastVersion.setAccessible(true);

                Field mVersionField = liveDataClass.getDeclaredField("mVersion");
                mVersionField.setAccessible(true);
                Object mVersion = mVersionField.get(this);

                mLastVersion.set(observerWrapper, mVersion);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("LiveDataBusX", e.getMessage());
            }

        }
    }
}
