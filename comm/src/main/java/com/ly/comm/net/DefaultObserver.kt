package com.ly.comm.net

import android.net.ParseException
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

abstract class DefaultObserver<T> : Observer<T> {
    override fun onSubscribe(d: Disposable) {}
    override fun onNext(t: T) {
        onSuccess(t)
        onFinish()
    }

    override fun onError(e: Throwable) {
        if (e is HttpException) {
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException
            || e is UnknownHostException
        ) {
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            onException(ExceptionReason.PARSE_ERROR)
        } else if (e is ApiException) {
            onFail(e.message)
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
        onFinish()
    }

    override fun onComplete() {}
    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    protected abstract fun onSuccess(response: T)

    /**
     * 服务器返回数据，但响应码不为success约定
     */
    private fun onFail(message: String?) {
        ToastUtils.showShort(message)
    }

    private fun onFinish() {}
    /**
     * 请求异常
     */
    private fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.CONNECT_ERROR -> ToastUtils.showShort("连接错误")
            ExceptionReason.CONNECT_TIMEOUT -> ToastUtils.showShort("连接超时")
            ExceptionReason.BAD_NETWORK -> ToastUtils.showShort("网络错误")
            ExceptionReason.PARSE_ERROR -> ToastUtils.showShort("解析错误")
            ExceptionReason.UNKNOWN_ERROR -> ToastUtils.showShort("未知错误")
            else -> ToastUtils.showShort("未知错误")
        }
    }

    /**
     * 请求网络失败原因
     */
    private enum class ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR
    }
}