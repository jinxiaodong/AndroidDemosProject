package com.jarvis.aidlserver

import android.graphics.BitmapFactory
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jarvis.aidlserver.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity(), App.OnGetClientDataCallback {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        App.application.setOnGetClientDataCallback(this)

        binding.send.setOnClickListener {

            sendLargeData()
        }
    }


    override fun onGetClientData(bytes: ByteArray?) {
        if (bytes != null && bytes.isNotEmpty()) {
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            if (bitmap != null) {
                binding.iv.setImageBitmap(bitmap)
            }
        }
    }


    //MainActivity.kt
    private fun sendLargeData() {
        try {
            /**
             * 读取assets目录下文件
             */
            val inputStream = assets.open("large.jpeg")

            /**
             * 将inputStream转换成字节数组
             */
            val byteArray = inputStream.readBytes()

            /**
             * 创建MemoryFile
             */
            val memoryFile = MemoryFile("image", byteArray.size)

            /**
             * 向MemoryFile中写入字节数组
             */
            memoryFile.writeBytes(byteArray, 0, 0, byteArray.size)

            /**
             * 获取MemoryFile对应的FileDescriptor
             */
            val fd = MemoryFileUtils.getFileDescriptor(memoryFile)

            /**
             * 根据FileDescriptor创建ParcelFileDescriptor
             */
            val pfd = ParcelFileDescriptor.dup(fd)

            /**
             * 发送数据
             */

            val message = Message.obtain().apply {
                what = 2
                obj = pfd
            }
            App.application.handler.sendMessage(message)

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}