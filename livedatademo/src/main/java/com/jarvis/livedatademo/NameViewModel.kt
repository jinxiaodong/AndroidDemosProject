package com.jarvis.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/21
 */
class NameViewModel : ViewModel() {

    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val userInfoLocal: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }
    val userInfoNet: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }

    val useLocal: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val aa = Transformations.switchMap(useLocal, fun(useLocal: Boolean): LiveData<UserInfo> {
        return if (useLocal) userInfoLocal else userInfoNet
    })


    val userInfoStr: LiveData<String> = Transformations.map(userInfoLocal) { user ->
        "姓名是：${user.name}，年龄：${user.age}"
    }

    fun updateUserInfo(info: UserInfo): Unit {
        userInfoLocal.value = info
    }

}