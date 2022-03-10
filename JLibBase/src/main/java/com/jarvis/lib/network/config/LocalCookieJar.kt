package com.jarvis.lib.network.config

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/11
 */
class LocalCookieJar : CookieJar {

    private val cache = mutableListOf<Cookie>()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {

        val invalidateCookies: MutableList<Cookie> = ArrayList()

        val validateCookies: MutableList<Cookie> = ArrayList()

        cache.forEach {
            if (it.expiresAt < System.currentTimeMillis()) {
                invalidateCookies.add(it)
            } else {
                validateCookies.add(it)
            }
        }
        cache.removeAll(invalidateCookies)
        return cache
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cache.addAll(cookies)
    }
}
