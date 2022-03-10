package com.jarvis.lib.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarvis.lib.ktx.yes
import com.jarvis.lib.liveData.event.EventLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
open class BaseViewModel : ViewModel() {

    private val jobs = mutableListOf<Job>()

    val dismissLoading by lazy { EventLiveData<Boolean>() }

    val showLoading by lazy { EventLiveData<String>() }

    /**
     * 协程 网络请求
     */
    protected fun serverAwait(
        needShowLoading: Boolean = false,
        loadingMsg: String = "加载中，请稍后...",
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch {
            needShowLoading.yes { showLoading.value = loadingMsg }
            block.invoke(this)
            dismissLoading.value = true
        }.addTo(jobs)
    }

    override fun onCleared() {
        super.onCleared()
        for (job in jobs) {
            job.cancel()
        }
    }

    private fun Job.addTo(list: MutableList<Job>) {
        list.add(this)
    }
}