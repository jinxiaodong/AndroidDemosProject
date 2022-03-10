package com.jarvis.lib.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
object ExceptionHandler {

    fun handleException(throwable: Throwable?): AppException {
        val ex: AppException
        throwable?.let {
            when (it) {
                is HttpException, is ConnectException -> {
                    ex = AppException(Error.NETWORK_ERROR, throwable)
                    return ex
                }

                is ConnectTimeoutException, is SocketTimeoutException -> {
                    ex = AppException(Error.TIMEOUT_ERROR, throwable)
                    return ex
                }

                is SSLException -> {
                    ex = AppException(Error.SSL_ERROR, throwable)
                    return ex
                }

                is JsonParseException, is JSONException, is MalformedJsonException, is ParseException -> {
                    ex = AppException(Error.PARSE_ERROR, throwable)
                    return ex
                }

                is UnknownHostException -> {
                    ex = AppException(Error.TIMEOUT_ERROR, throwable)
                    return ex
                }

                else -> {
                    ex = AppException(Error.UNKNOWN, throwable)
                    return ex
                }
            }
        }
        ex = AppException(Error.UNKNOWN, throwable)

        return ex
    }

}