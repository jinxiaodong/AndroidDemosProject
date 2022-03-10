package com.jarvis.lib.liveData

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.jarvis.lib.base.BaseActivity
import com.jarvis.lib.base.BaseFragment
import com.jarvis.lib.base.BaseViewModel
import com.jarvis.lib.ktx.dismissLoading
import com.jarvis.lib.ktx.showLoading

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */

fun ViewModel.observeLoadingUI(owner: LifecycleOwner) {
    if (this is BaseViewModel && owner is BaseActivity<*>) {
        showLoading.observe(owner) {
            owner.showLoading(it)
        }
        dismissLoading.observe(owner) {
            owner.dismissLoading()
        }
    }
    if (this is BaseViewModel && owner is BaseFragment) {
        showLoading.observe(owner) {
            owner.showLoading(it)
        }
        dismissLoading.observe(owner) {
            owner.dismissLoading()
        }
    }
}



