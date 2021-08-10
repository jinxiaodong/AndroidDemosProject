package com.jarvis.kotlindemo.http

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
interface EntityCallback<T> {
    fun onSuccess(entity: T)

    fun onFailure(message: String)
}