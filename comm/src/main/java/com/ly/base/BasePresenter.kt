package com.ly.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference

abstract class BasePresenter<V : IView>(view: IView) : IPresenter {

    @Suppress("UNCHECKED_CAST")
    protected var mViewRef: WeakReference<V>? = WeakReference(view as V)

    override fun oncreate(owner: LifecycleOwner) {

    }

    override fun onDestroy(owner: LifecycleOwner) {
        mViewRef?.clear()
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, lifecycleEvent: Lifecycle.Event) {

    }
}