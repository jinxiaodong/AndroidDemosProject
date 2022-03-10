package com.jarvis.lib.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.gyf.immersionbar.ImmersionBar
import com.jarvis.lib.R
import com.jarvis.lib.ktx.bindView
import com.jarvis.lib.ktx.viewLifeCycleOwner
import com.jarvis.lib.liveData.observeLoadingUI
import com.jarvis.lib.network.manager.NetState
import com.jarvis.lib.network.manager.NetWorkStateManager
import com.jarvis.lib.view.LoadingDialog

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
abstract class BaseActivity<bindingType : ViewDataBinding> : AppCompatActivity() {


    lateinit var loadingDialog: LoadingDialog

    protected lateinit var binding: bindingType


    @LayoutRes
    abstract fun getContentLayout(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindView(getContentLayout())
        loadingDialog = LoadingDialog(this)
        createObserver()
        initConfig()
        //初始化沉浸式
        initImmersionBar()
        initView()
        initData()

    }


    abstract fun initConfig()

    abstract fun initView()


    abstract fun initData()

    open fun onNetworkStateChanged(netState: NetState) {

    }

    open fun initImmersionBar() {
        ImmersionBar.with(this).navigationBarColor(R.color.color_008577).init()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (this::binding.isLateinit) {
            binding.unbind()
        }
    }


    private fun createObserver() {
        observerNetWorkState()
    }

    private fun observerNetWorkState() {
        NetWorkStateManager.instance.netWorkStateCallback.observe(this) { onNetworkStateChanged(it) }
    }

    /**
     * 如果网络需要关联 loadingUI，调用该方法注册
     */
    protected fun observeLoadingUI(viewModel: BaseViewModel) {
        viewModel.observeLoadingUI(this)
    }

    /**
     * 扩展liveData的observe函数
     */
    protected fun <T> LiveData<T>.observerKt(block: (T?) -> Unit) {
        this.observe(viewLifeCycleOwner) { data -> block(data) }
    }
}