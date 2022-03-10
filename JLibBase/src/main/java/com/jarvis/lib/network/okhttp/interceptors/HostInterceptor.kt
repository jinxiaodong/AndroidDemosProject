package com.jarvis.lib.network.okhttp.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/11
 */
class HostInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

//        val oldHost = originalRequest.url.host
        val oldHostString = originalRequest.url.toString()
        //替换Host策略


        val newBuilder = originalRequest.newBuilder()
        newBuilder.url(oldHostString)

        return chain.proceed(newBuilder.build())

    }
}