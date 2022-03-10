package com.jarvis.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.jarvis.lib.network.manager.NetType

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
fun isNetWorkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        val info = connectivityManager.activeNetworkInfo
        return null != info && info.isAvailable
    } else {
        val netWork = connectivityManager.activeNetwork ?: return false
        val nc = connectivityManager.getNetworkCapabilities(netWork) ?: return false
        return nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}

/**
 * 是否Wifi 连接
 */
fun isWifiConnected(context: Context): Boolean {
    val connectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        val wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return null != wifiInfo && wifiInfo.isAvailable
    } else {
        val netWork = connectivityManager.activeNetwork ?: return false
        val nc = connectivityManager.getNetworkCapabilities(netWork) ?: return false
        return nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }
}

/**
 * 是否数据连接
 */
fun isMobileConnected(context: Context): Boolean {
    val connectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        val mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return null != mobileInfo && mobileInfo.isAvailable
    } else {
        val netWork = connectivityManager.activeNetwork ?: return false
        val nc = connectivityManager.getNetworkCapabilities(netWork) ?: return false
        return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }

}

/**
 * 获取当前网络类型
 */
fun getCurrentNetWorkType(context: Context): NetType {
    return if (isNetWorkAvailable(context)) {
        when {
            isWifiConnected(context) -> NetType.NETWORK_WIFI
            isMobileConnected(context) -> NetType.NETWORK_MOBILE
            else -> NetType.NETWORK_UNKNOWN
        }
    } else NetType.NETWORK_NO

}


