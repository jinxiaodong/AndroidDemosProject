package com.jarvis.kotlindemo.simple

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/10
 */

interface API {

    fun lessons(): Call<Any>
}


val RETROFIT = Retrofit.Builder()
    .baseUrl("https://api.hencoder.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

//
//fun <T> create(clazz: Class<T>): T {
//    return RETROFIT.create(clazz)
//}
//
//
//fun main() {
//    println(create(API::class.java))
//
//}

inline fun <reified T> create(): T {
    return RETROFIT.create(T::class.java)
}


fun main() {
    println(create<API>())

}