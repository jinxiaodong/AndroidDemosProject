package com.jarvis.lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.jarvis.lib.liveData.observeLoadingUI
import com.jarvis.lib.view.LoadingDialog

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/22
 */

abstract class BaseFragment : Fragment {
    lateinit var loadingDialog: LoadingDialog

    /**
     * 无参构造函数
     */
    constructor() : super()

    /**
     * 可以填入layout布局的构造函数，使用viewBinding的方便
     * [layout] layout布局文件的id
     */
    constructor(@LayoutRes layout: Int) : super(layout)

    //UI的viewDataBinding对象
    private var binding: ViewDataBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bindView(view, savedInstanceState)
        binding?.lifecycleOwner = viewLifecycleOwner
        loadingDialog = LoadingDialog(view.context)
        initConfig()
        initData()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding

    /**
     * view初始化后的必要配置
     */
    open fun initConfig() {
//        LogUtils.d("${this.javaClass.simpleName} 初始化 initConfig")
    }

    /**
     * view初始化后的必要数据
     */
    open fun initData() {
//        LogUtils.d("${this.javaClass.simpleName} 初始化 initData")
    }




    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
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
        this.observe(viewLifecycleOwner) { data -> block(data) }
    }
}