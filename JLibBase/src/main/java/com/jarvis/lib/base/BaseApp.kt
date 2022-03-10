package com.jarvis.lib.base

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.blankj.utilcode.util.LogUtils
import com.jarvis.lib.core.ApplicationHolder
import com.jarvis.lib.network.manager.JNetWorkCallback
import com.jarvis.lib.network.manager.NetWorkStateReceiver

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
open class BaseApp : Application() {
    companion object {
        private lateinit var instance: Application

        fun getInstance(): Application {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationHolder.init(this)
        registerNetWorkStateChangeListener()
        LogUtils.getConfig().globalTag = "Jarvis123"


    }

    private fun registerNetWorkStateChangeListener() {
        this.registerReceiver(
            NetWorkStateReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        //弃用后的适配方案
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.registerNetworkCallback(
            NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build(), JNetWorkCallback()
        )

    }
}