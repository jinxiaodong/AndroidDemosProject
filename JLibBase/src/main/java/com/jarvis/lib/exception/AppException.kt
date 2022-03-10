package com.jarvis.lib.exception

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
class AppException : Exception {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var errorLog: String? = null
    var throwable: Throwable? = null

    constructor(
        errorCode: Int,
        errorMsg: String?,
        errorLog: String? = "",
        throwable: Throwable? = null
    ) : super(errorMsg) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
        this.errorLog = errorLog ?: this.errorMsg
        this.throwable = throwable
    }

    constructor(error: Error, cause: Throwable?) {
        this.errorCode = error.getErrorCode()
        this.errorMsg = error.getErrorMsg()
        this.errorLog = cause?.message
        this.throwable = cause
    }
}