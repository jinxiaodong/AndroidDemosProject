package com.jarvis.databindingdemo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/5
 */
class User(s: String, s1: String) : BaseObservable() {
//    @get:Bindable
    var name: String = s
    set(value) {
        field = value
//        notifyPropertyChanged(BR.name)
    }

//    @get:Bindable
    var pwd: String = s1
    set(value) {
        field = value
//        notifyPropertyChanged(BR.pwd)
    }
}