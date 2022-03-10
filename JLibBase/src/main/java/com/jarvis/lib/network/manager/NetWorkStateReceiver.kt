package com.jarvis.lib.network.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.blankj.utilcode.util.LogUtils
import com.jarvis.network.getCurrentNetWorkType
import com.jarvis.network.isNetWorkAvailable

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */

class NetWorkStateReceiver : BroadcastReceiver() {
    //冷启动会多调一次
    var isInit: Boolean = true
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            if (!isInit) {
                if (context != null && isNetWorkAvailable(context)) {
                    val currentNetWorkType = getCurrentNetWorkType(context)
                    LogUtils.i("有网")
                    NetWorkStateManager.instance.netWorkStateCallback.value?.let {
                        if (!it.available) {
                            //之前没网，后面有，才通知
                            NetWorkStateManager.instance.netWorkStateCallback.value =
                                NetState(true).also { netState ->
                                    netState.netType = currentNetWorkType
                                }
                        }
                        return
                    }
                    NetWorkStateManager.instance.netWorkStateCallback.value = NetState(true).also {
                        it.netType = currentNetWorkType
                    }
                } else {
                    LogUtils.i("无网")
                    NetWorkStateManager.instance.netWorkStateCallback.value?.let {
                        if (it.available) {
                            //之前有网，后面没网了，才通知
                            NetWorkStateManager.instance.netWorkStateCallback.value =
                                NetState(false).also { netState ->
                                    netState.netType = NetType.NETWORK_NO
                                }
                        }
                        return
                    }
                    NetWorkStateManager.instance.netWorkStateCallback.value =
                        NetState(false).also { netState ->
                            netState.netType = NetType.NETWORK_NO
                        }
                }
            }
        }
        isInit = false
    }
}