package com.jarvis.lib.network.okhttp.interceptors

import okhttp3.Interceptor
import okhttp3.Response

 /**
  * @author jinxiaodong
  * @description： 重试请求拦截器
  * @date 2022/2/11
  */
class RetryInterceptor(private val maxRetry: Int = 0) : Interceptor {

    private var retryTimes = 0


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var response = chain.proceed(request)
        while (!response.isSuccessful && retryTimes < maxRetry) {
            retryTimes++
            response = chain.proceed(request)
        }
        return response
    }
}