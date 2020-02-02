package com.ly.login.app

import android.app.Application
import com.ly.base.BaseApp
import com.ly.comm.ServiceFactory
import com.ly.login.service.UserInfoService

class LoginApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        ServiceFactory.instance.accountService = UserInfoService()
    }

    override fun initModuleData(application: Application) {
    }
}