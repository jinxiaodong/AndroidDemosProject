package com.jarvis.livedatademo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/7/28
 */
public class NameViewModel extends ViewModel {

    private MutableLiveData<String> currentName;
    private int i = 0;

    public int getI() {
        return i++;
    }


    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<>();
        }
        return currentName;
    }


}
