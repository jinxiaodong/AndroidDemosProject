package com.jarvis.livedatademo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jarvis.livedatademo.databinding.ActivityMainBinding

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/15
 */
class LiveDataActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding

    private val viewModel: LiveDataViewModel by viewModels { LiveDataVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bind.lifecycleOwner = this
        bind.viewmodel = viewModel

    }
}