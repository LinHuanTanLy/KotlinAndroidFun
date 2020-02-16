package com.ly.base

import com.trello.rxlifecycle3.LifecycleTransformer
import io.reactivex.ObservableTransformer

interface IView {

    fun showTs(msg: String)
    fun finishAc()
    fun <T> bindThread(): ObservableTransformer<T, T>
    fun <T> bindLifeCycle(): LifecycleTransformer<T>
    fun <T> bindLoading(): ObservableTransformer<T, T>
}