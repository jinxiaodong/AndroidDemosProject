package com.jarvis.aidlclient

import android.app.Service
import android.content.Intent
import android.os.*
import java.io.FileInputStream

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/14
 */
class AidlService : Service() {

//    private val callbacks= RemoteCallbackList<String>()

    private val mStub: IMyAidlInterface.Stub = object : IMyAidlInterface.Stub() {

        override fun client2server(pfd: ParcelFileDescriptor) {
            /**
             * 从ParcelFileDescriptor中获取FileDescriptor
             */
            val fileDescriptor = pfd.fileDescriptor

            /**
             * 根据FileDescriptor构建InputStream对象
             */
            val fis = FileInputStream(fileDescriptor)

            /**
             * 从InputStream中读取字节数组
             */
            val data = fis.readBytes()

            val message = Message().apply {
                what = 1
                obj = data
            }
            App.application.handler.sendMessage(message)
        }

    }

    override fun onBind(intent: Intent?): IBinder {
        return mStub
    }


    override fun onCreate() {
        super.onCreate()
//        App.application.setOnSendClientDataCallback(object :App.OnSendClientDataCallback{
//            override fun onSendClientData(pfd: ParcelFileDescriptor) {
//                serverToClient(pfd)
//            }
//
//        })

    }

    private fun serverToClient(pfd: ParcelFileDescriptor) {
//        val n=callbacks.beginBroadcast()
//        for(i in 0 until n){
//            val callback=callbacks.getBroadcastItem(i);
//            if (callback!=null){
//                try {
//                    callback.server2client(pfd)
//                } catch (e: RemoteException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        callbacks.finishBroadcast()
    }
}