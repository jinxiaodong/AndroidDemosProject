package com.jarvis.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jarvis.databindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var user: User
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        user = User("1", "11")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = user
//        user1.notifyPropertyChanged(BR.user)


        Thread {
            for (index in 0..10) {
                Thread.sleep(1000)
//                binding.user = User("$index","$index")
                user.name = "Jarvis$index"
                binding.user1 = User1("user1","user1")
//                user1.name = (DataBindingUtil.getDefaultComponent() == null).toString()
            }
        }.start()
    }
}