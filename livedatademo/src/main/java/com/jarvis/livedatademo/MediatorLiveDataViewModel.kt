package com.jarvis.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/21
 */
class MediatorLiveDataViewModel : ViewModel() {

    val userInfo: LiveData<String> = MutableLiveData()
    val user: LiveData<String> = MutableLiveData()

    val mergerLiveData = MediatorLiveData<String>()


    fun merge(): Unit {
        mergerLiveData.addSource(userInfo) { value ->
            mergerLiveData.value = value
        }
        mergerLiveData.addSource(user) { value ->
            mergerLiveData.value = value
        }
    }
}