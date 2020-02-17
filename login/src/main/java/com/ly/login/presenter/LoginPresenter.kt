package com.ly.login.presenter

import com.ly.base.BasePresenter
import com.ly.base.IView
import com.ly.bean.ChaptersBean
import com.ly.comm.net.DefaultObserver
import com.ly.login.contract.ILogin
import com.ly.login.manager.UserInfoManager
import com.ly.login.module.LoginModule

open class LoginPresenter(view: IView) : BasePresenter<ILogin.View>(view) {

    private val loginModule by lazy {
        LoginModule()
    }


    fun login() {
        UserInfoManager.setUserName("linhuantan")
    }

    fun logout() {
        UserInfoManager.setUserName("")
    }

    fun getChapters() {
        loginModule.getChapters()
            .compose(mViewRef?.get()?.bindLoading())
            .compose(mViewRef?.get()?.bindThread())
            .compose(mViewRef?.get()?.bindLifeCycle())
            .subscribe(object : DefaultObserver<List<ChaptersBean>>() {
                override fun onSuccess(response: List<ChaptersBean>) {
                    mViewRef?.get()?.onSuccess(response)
                }
            })
    }
}