package com.jarvis.customerview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.jarvis.customerview.databinding.ActivitySlideBinding
import com.jarvis.lib.base.BaseActivity

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/3/9
 */
class SlideActivity :BaseActivity<ActivitySlideBinding>() {



    override fun getContentLayout()= R.layout.activity_slide

    override fun initConfig() {
    }

    override fun initView() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun initData() {
    }
}