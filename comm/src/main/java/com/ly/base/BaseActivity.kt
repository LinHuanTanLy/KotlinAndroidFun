package com.ly.base

import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.ly.comm.net.DialogUtils
import com.trello.rxlifecycle3.LifecycleTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseActivity<V : IView, T : BasePresenter<V>> : BaseSimpleActivity(),
    IView {
    protected lateinit var presenter: T
    abstract fun createPresenter(): T
    override fun initP() {
        super.initP()
        presenter = createPresenter()
        lifecycle.addObserver(presenter)
    }

    override fun showTs(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun finishAc() {
        finish()
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
        return DialogUtils.applyProgressBar(this, "请稍候")
    }
}
