package com.jarvis.lib.exception

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
enum class Error(private val errorCode: Int, private val errorMsg: String) {

    UNKNOWN(1000, "请求失败，请稍后重试"),

    PARSE_ERROR(1001, "解析错误，请稍后重试"),

    NETWORK_ERROR(1002, "网络连接错误，请稍后重试"),

    SSL_ERROR(1003, "证书出错，请稍后重试"),

    TIMEOUT_ERROR(1006, "网络连接超时，请稍后重试");

    fun getErrorMsg(): String {
        return errorMsg
    }

    fun getErrorCode(): Int {
        return errorCode
    }
}