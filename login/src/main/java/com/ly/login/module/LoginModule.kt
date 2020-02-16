package com.ly.login.module

import com.ly.base.BaseModule
import com.ly.bean.ChaptersBean
import com.ly.comm.net.Api
import com.ly.comm.net.api.ApiService
import com.ly.comm.net.api.ApiServiceUtil
import com.ly.comm.net.api.LoginApiService
import io.reactivex.Observable

class LoginModule : BaseModule<LoginApiService>() {
    override fun createApiService(): LoginApiService = ApiServiceUtil.getApiService()

    fun getChapters(): Observable<List<ChaptersBean>> {
        return api.getChapters().map(Api.HandleFuc())
    }
}