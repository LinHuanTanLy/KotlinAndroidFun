package com.ly.login.ac

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ly.base.BaseActivity
import com.ly.bean.ChaptersBean
import com.ly.comm.net.DefaultObserver
import com.ly.comm.net.api.ApiServiceUtil
import com.ly.comm.net.api.LoginApiService
import com.ly.login.R
import com.ly.login.contract.ILogin
import com.ly.login.presenter.LoginPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = "/login/ac")
class LoginAc : BaseActivity<ILogin.View, LoginPresenter>(), ILogin.View {

    override fun getContentView() = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {
        presenter.getChapters()
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun createPresenter() = LoginPresenter(this)

    override fun onSuccess(list: List<ChaptersBean>) {

        tvLogin.text = list.toString()
    }
}

