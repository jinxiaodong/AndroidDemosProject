package com.jarvis.lib.network.okhttp.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/11
 */
class CommonHeadersInterceptors : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newBuilder = originalRequest.newBuilder()

//        newBuilder.addHeader()
        return chain.proceed(newBuilder.build())
    }
}