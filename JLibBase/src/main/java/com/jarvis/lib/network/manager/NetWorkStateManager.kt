package com.jarvis.lib.network.manager

import com.jarvis.lib.liveData.event.EventLiveData

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
class NetWorkStateManager private constructor(){

    val netWorkStateCallback  = EventLiveData<NetState>()


    companion object{
        val instance: NetWorkStateManager by lazy(mode= LazyThreadSafetyMode.SYNCHRONIZED) {
            NetWorkStateManager()
        }
    }
}
