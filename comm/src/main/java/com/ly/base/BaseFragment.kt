package com.ly.base

import android.app.Activity
import com.blankj.utilcode.util.ToastUtils
import com.ly.comm.net.DialogUtils
import com.trello.rxlifecycle3.LifecycleTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseFragment<V : IView, T : BasePresenter<V>> : BaseSimpleFragment(), IView {

    protected lateinit var p: T
    private lateinit var ac: Activity
    protected abstract fun createPresenter(): T


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        ac = activity
    }

    override fun initPresenter() {
        super.initPresenter()
        p = createPresenter()
        lifecycle.addObserver(p)
    }


    override fun showTs(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun finishAc() {

    }

    override fun <T> bindThread(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun <T> bindLifeCycle(): LifecycleTransformer<T> {
        return bindToLifecycle()
    }

    override fun <T> bindLoading(): ObservableTransformer<T, T> {
        return DialogUtils.applyProgressBar<T>(ac, "请稍候...")
    }
}