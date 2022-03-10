package com.jarvis.lib.network.manager

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
enum class NetType(private val netWorkType: String) {

    NETWORK_WIFI("WIFI"),

    //    NETWORK_5G("5G"),
//    NETWORK_4G("4G"),
//    NETWORK_3G("3G"),
    NETWORK_MOBILE("MOBILE"),
    NETWORK_UNKNOWN("UNKNOWN"),
    NETWORK_NO("NO");

    fun getNetWorkType(): String {
        return netWorkType
    }

}