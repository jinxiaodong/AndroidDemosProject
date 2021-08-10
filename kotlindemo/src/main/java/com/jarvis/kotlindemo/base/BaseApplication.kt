package com.jarvis.kotlindemo.base

import android.app.Application
import android.content.Context

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
class BaseApplication : Application() {


    companion object {

        @JvmStatic
        @get:JvmName("currentApplication")
        lateinit var currentApplication: Context
            private set

    }

    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }
}