package com.jarvis.kotlindemo.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.jarvis.kotlindemo.base.BaseApplication
import com.jarvis.kotlindemo.R

/**
 * @author jinxiaodong
 * @description：当一个类使用object 修饰，该类中所有成员和方法都是静态的
 * @date 2021/8/9
 */
@SuppressLint("StaticFieldLeak")
object CacheUtils {

    private val context: Context = BaseApplication.currentApplication

    private val SP =
        context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE)


    fun save(key: String, value: String) = SP.edit().putString(key, value).apply()

    fun get(key: String) = SP.getString(key, "")


}