package com.jarvis.lib.network.okhttp

import android.os.Environment
import com.jarvis.lib.network.config.HttpsUtils
import com.jarvis.lib.network.config.LocalCookieJar
import com.jarvis.lib.network.okhttp.interceptors.CommonHeadersInterceptors
import com.jarvis.lib.network.okhttp.interceptors.HostInterceptor
import com.jarvis.lib.network.okhttp.interceptors.HttpLogInterceptor
import com.jarvis.lib.network.okhttp.interceptors.RetryInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/11
 */
class OkHttpClientFactory {

    companion object {

        var maxRetry = 0

        fun getDefaultOkHttpClient(): OkHttpClient {
            val sslSocketFactory = HttpsUtils.getSslSocketFactory()
            return OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .cache(Cache(File(Environment.getDataDirectory(), "okhttp/cache"), 1024))
                .cookieJar(LocalCookieJar())
                .addInterceptor(HostInterceptor())
                .addNetworkInterceptor(CommonHeadersInterceptors())
                .addNetworkInterceptor(HttpLogInterceptor{
                    logLevel(HttpLogInterceptor.LogLevel.BODY)
                    colorLevel(HttpLogInterceptor.ColorLevel.INFO)
                    logTag("Jarvis_Okhttp:")
                })
                .addNetworkInterceptor(RetryInterceptor(maxRetry))
                .sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager)
                .build()

        }
    }
}