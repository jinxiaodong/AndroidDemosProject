package com.jarvis.aidlserver

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jarvis.aidlserver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    App.OnGetClientDataCallback {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        App.application.setOnGetClientDataCallback(this)
    }


    override fun onGetClientData(bytes: ByteArray?) {
        if (bytes != null && bytes.isNotEmpty()) {
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            if (bitmap != null) {
                binding.iv.setImageBitmap(bitmap)
            }
        }
    }
}