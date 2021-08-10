package com.jarvis.kotlindemo.simple

import retrofit2.Call
import retrofit2.Retrofit

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