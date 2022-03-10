package com.jarvis.lib.network.response

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/23
 */
data class ApiResponse<T>(val errorCode: Int, val errorMsg: String?, val data: T) {

    companion object {
        const val SERVER_CODE_FAILED = 1
        const val SERVER_CODE_SUCCESS = 0
    }


}


/**
 * 接口成功，但是业务返回code不是1的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T> ApiResponse<T>.onBizError(crossinline block: (code: Int, message: String) -> Unit): ApiResponse<T> {
    contract {
        callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    if (errorCode != ApiResponse.SERVER_CODE_SUCCESS) {
        block.invoke(errorCode, errorMsg ?: "Error Message Null")
    }
    return this
}


/**
 * 接口成功且业务成功code == 1的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T> ApiResponse<T>.onBizOK(crossinline action: (code: Int, data: T?, message: String?) -> Unit): ApiResponse<T> {
    contract {
        callsInPlace(action, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    if (errorCode == ApiResponse.SERVER_CODE_SUCCESS) {
        action.invoke(errorCode, this.data, errorMsg)
    }
    return this
}
