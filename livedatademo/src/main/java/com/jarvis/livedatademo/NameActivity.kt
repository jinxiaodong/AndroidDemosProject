package com.jarvis.livedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jarvis.livedatademo.databinding.ActivityName2Binding

class NameActivity : AppCompatActivity() {

    private lateinit var bind: ActivityName2Binding
    private val model: NameViewModel by viewModels()
    private lateinit var userInfo1: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userInfo1 = UserInfo("11", 1)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_name2)

        //创建观察者
        val nameObserver = Observer<String> { newName ->
            //更新UI
            bind.desc.text = newName
        }
        //订阅事件，同时传入 LifecycleOwner 和观察者
        model.currentName.observe(this, nameObserver)

        model.aa.observe(this) { userInfo ->
            userInfo1 = userInfo
            bind.desc.text = userInfo.name + userInfo.age
        }

        bind.btnName.setOnClickListener {
            model.useLocal.value = !(model.useLocal.value ?: true)
        }
        bind.changeUserName.setOnClickListener {
            model.userInfoNet.value = UserInfo("网络", 1 + userInfo1.age)
            model.userInfoLocal.value = UserInfo("本地", 1 + userInfo1.age)
        }

    }
}