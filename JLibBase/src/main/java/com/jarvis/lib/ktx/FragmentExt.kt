package com.jarvis.lib.ktx

import com.jarvis.lib.base.BaseFragment


/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */


fun BaseFragment.showLoading(message: String = "正在加载中,请稍后...") {
    context?.let {
        this.loadingDialog.showDialog(it)
    }
}

fun BaseFragment.showError1() {

}

fun BaseFragment.showError2() {

}


fun BaseFragment.dismissLoading() {
    this.loadingDialog.dismissDialog()
}