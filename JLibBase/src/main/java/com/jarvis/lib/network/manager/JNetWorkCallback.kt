package com.jarvis.lib.network.manager

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import com.blankj.utilcode.util.LogUtils

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
class JNetWorkCallback : ConnectivityManager.NetworkCallback() {


    /**
     * 网络连接成功
     */
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        LogUtils.i("网络连接成功")
    }


    /**
     * 网络断开连接
     */
    override fun onLost(network: Network) {
        super.onLost(network)
        LogUtils.i("网络断开连接")

    }

    /**
     * 网络正在断开连接
     */
    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        LogUtils.i("网络正在断开连接")

    }

    /**
     * 无网络
     */
    override fun onUnavailable() {
        super.onUnavailable()
        LogUtils.i("网络连接超时或网络连接不可达")

    }

    /**
     * 网络状态已修改（网络依然可用）
     */
    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        LogUtils.i("网络连接改变")
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            //可以上网
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                //wifi
            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                //数据网络
            } else {
                //其他网络 蓝牙等
            }

        }

    }

    /**
     * 访问的网络阻塞状态变化
     */
    override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
        super.onBlockedStatusChanged(network, blocked)
    }

    /**
     * 网络连接属性发生变化
     */
    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties)
    }

}