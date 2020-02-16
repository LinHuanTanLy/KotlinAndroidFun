package com.ly.comm.net

import android.app.Activity
import io.reactivex.ObservableTransformer
import java.lang.ref.WeakReference

object DialogUtils {
    fun <T> applyProgressBar(
        activity: Activity, msg: String?
    ): ObservableTransformer<T, T> {
        val activityWeakReference =
            WeakReference(activity)
        DialogHelper.getInstance().showProgress(activityWeakReference.get()!!, msg!!)
        return ObservableTransformer { upstream ->
            upstream.doOnSubscribe { }.doOnTerminate {
                var context: Activity
                if (activityWeakReference.get().also { context = it!! } != null
                    && !context.isFinishing
                ) {
                    DialogHelper.getInstance().dismissProgress()
                }
            }.doOnSubscribe { }
        }
    }
}
