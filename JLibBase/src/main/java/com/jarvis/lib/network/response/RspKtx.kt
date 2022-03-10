package com.jarvis.lib.network.response

import com.jarvis.lib.exception.AppException
import com.jarvis.lib.exception.Error
import retrofit2.Call
import retrofit2.await
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/23
 */

suspend fun <T : Any> Call<T>.serverData(): DataResult<T> {

    var result: DataResult<T> = DataResult.Loading

    kotlin.runCatching {
        this.await()
    }.onFailure {
        result = DataResult.Error(AppException(Error.NETWORK_ERROR, it))
        it.printStackTrace()
    }.onSuccess {
        result = DataResult.Success(it)
    }

    return result
}


/**
 * 扩展用于处理网络返回数据结果 网络接口请求成功，但是业务成功与否，另一说
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(
    action: R.() -> Unit
): DataResult<R> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (succeeded) action.invoke((this as DataResult.Success<R>).data)
    return this
}


/**
 * 这是网络请求出现错误的时候，的回调
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onFailure(
    action: (exception: Throwable) -> Unit
): DataResult<R> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Error) action.invoke(exception)
    return this
}
