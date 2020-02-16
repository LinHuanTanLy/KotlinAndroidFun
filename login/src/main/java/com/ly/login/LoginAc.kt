package com.ly.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ly.bean.ChaptersBean
import com.ly.comm.net.Api
import com.ly.comm.net.DefaultObserver
import com.ly.comm.net.api.ApiServiceUtil
import com.ly.comm.net.api.LoginApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = "/login/ac")
class LoginAc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvLogin.setOnClickListener {
            ApiServiceUtil.getApiService<LoginApiService>()
                .getChapters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<Any>() {
                    override fun onSuccess(response: Any) {
                        Log.d("lht", "the response is $response")
                    }
                })
        }


    }
}

