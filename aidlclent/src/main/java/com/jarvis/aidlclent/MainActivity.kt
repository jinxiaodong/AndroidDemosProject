package com.jarvis.aidlclent

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jarvis.aidlclent.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var mStub: IMyAidlInterface? = null

    private lateinit var binding: ActivityMainBinding

    private var serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            mStub = IMyAidlInterface.Stub.asInterface(binder)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mStub = null
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnBind.setOnClickListener {
            bindService()
        }

        binding.send.setOnClickListener {

        }

    }

    private fun bindService() {
        if (mStub != null) {
            return
        }
        val intent = Intent("com.jarvis.aidlserver.AidlService")
        intent.setClassName("com.jarvis.aidlserver", "com.jarvis.aidlserver.AidlService")
        try {
            if (bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)) {
                Toast.makeText(this, "bind ok", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "bind fail", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        if(mStub!=null) {
            unbindService(serviceConnection)
        }
        super.onDestroy()
    }


    //MainActivity.kt
    private fun sendLargeData() {
        if (mStub == null) {
            return
        }
        try {
            /**
             * 读取assets目录下文件
             */
            val inputStream = assets.open("large.jpg")

            /**
             * 将inputStream转换成字节数组
             */
            val byteArray=inputStream.readBytes()

            /**
             * 创建MemoryFile
             */
            val memoryFile= MemoryFile("image", byteArray.size)

            /**
             * 向MemoryFile中写入字节数组
             */
            memoryFile.writeBytes(byteArray, 0, 0, byteArray.size)

            /**
             * 获取MemoryFile对应的FileDescriptor
             */
            val fd=MemoryFileUtils.getFileDescriptor(memoryFile)

            /**
             * 根据FileDescriptor创建ParcelFileDescriptor
             */
            val pfd= ParcelFileDescriptor.dup(fd)

            /**
             * 发送数据
             */
            mStub?.client2server(pfd)

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

}