package com.jarvis.aidlclient

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.ParcelFileDescriptor

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/14
 */
class App : Application() {

    companion object {
        lateinit var application: App
    }

    /**
     * 从客户端接收到数据回调
     */
    private var getDataCallback: OnGetClientDataCallback? = null

    private var sendDataCallback: OnSendClientDataCallback? = null

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when (msg.what) {
                1 -> getDataCallback?.onGetClientData(msg.obj as ByteArray)
                2 -> sendDataCallback?.onSendClientData(msg.obj as ParcelFileDescriptor)
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        application = this

    }

    fun setOnGetClientDataCallback(callback: OnGetClientDataCallback?) {
        this.getDataCallback = callback
    }

    fun setOnSendClientDataCallback(callback: OnSendClientDataCallback?) {
        this.sendDataCallback = callback
    }


    interface OnGetClientDataCallback {
        fun onGetClientData(bytes: ByteArray?)
    }

    interface OnSendClientDataCallback {
        fun onSendClientData(pfd: ParcelFileDescriptor)
    }
}