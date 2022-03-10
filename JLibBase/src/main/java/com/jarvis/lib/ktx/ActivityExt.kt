package com.jarvis.lib.ktx

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.app.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.jarvis.lib.base.BaseActivity

/**
 * @author jinxiaodong
 * @description： Activity 方法扩展
 * @date 2022/2/10
 */

inline fun <reified T : Activity> Activity.startActivity(finishSelf: Boolean = false) {

    startActivity(Intent(this, T::class.java))
    finishSelf.yes {
        finish()
    }

}


inline fun <reified T : Activity> Activity.startActivity(extras: Bundle) {
    startActivity(Intent(this, T::class.java).putExtras(extras))
}

/**
 * Activity中使用DataBinding时setContentView的简化
 * [layout] 布局文件
 * @return 返回一个Binding的对象实例
 */

 fun <T : ViewDataBinding> Activity.bindView(@LayoutRes layout: Int): T {
    return DataBindingUtil.setContentView(this, layout)
}


/**
 * Activity中使用DataBinding时setContentView的简化
 * [layout] 布局文件
 * @return 返回一个Binding的对象实例 T 类型的 可null的
 */
 fun <T : ViewDataBinding> Activity.bindView(view: View): T? {
    return DataBindingUtil.bind(view)
}

/**
 * 扩展lifeCycleOwner属性，便于和Fragment之间使用lifeCycleOwner 一致性
 */
val ComponentActivity.viewLifeCycleOwner: LifecycleOwner
    get() = this


fun BaseActivity<*>.showLoading(message: String = "正在加载中,请稍后...") {
    this.loadingDialog.showDialog(this)
}

fun BaseActivity<*>.showError2() {

}


fun BaseActivity<*>.dismissLoading() {
    this.loadingDialog.dismissDialog()

}
