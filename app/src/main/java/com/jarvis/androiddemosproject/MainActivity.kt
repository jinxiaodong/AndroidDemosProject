package com.jarvis.androiddemosproject

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.jarvis.androiddemosproject.databinding.ActivityMainBinding
import com.jarvis.customerview.SlideActivity
import com.jarvis.lib.base.BaseActivity

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/25
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getContentLayout() = R.layout.activity_main

    override fun initConfig() {
    }

    override fun initView() {

        binding.apply {
            recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            val mainAdapter = MainAdapter(DataUtil.getMainData())
            recycleView.adapter = mainAdapter
            mainAdapter.setOnItemClickListener { adapter, view, position ->
                when (position) {
                    0 -> {}
                    1 -> {}
                    2 -> {
                        jumpToCustomerView()
                    }
                }
            }
        }
    }

    private fun jumpToCustomerView() {
        startActivity(Intent(this, SlideActivity::class.java))
    }

    override fun initData() {
    }
}