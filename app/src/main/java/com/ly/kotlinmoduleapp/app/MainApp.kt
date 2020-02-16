package com.ly.kotlinmoduleapp.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ApiUtils
import com.ly.base.BaseApp
import com.ly.base.CommBaseApp
import com.ly.base.appConf.GlobalAppConf
import com.ly.comm.net.Api

class MainApp : CommBaseApp() {
    override fun initModuleApp(application: Application) {
        for (app in GlobalAppConf.appList) {
            val clazz = Class.forName(app)
            val baseApp = clazz.newInstance() as BaseApp
            baseApp.initModuleApp(this)
        }
    }

    override fun initModuleData(application: Application) {
        for (app in GlobalAppConf.appList) {
            val clazz = Class.forName(app)
            val baseApp = clazz.newInstance() as BaseApp
            baseApp.initModuleData(this)
        }
    }

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
        ARouter.openLog()
        ARouter.openDebug()
        Api.getInstance().init(this)
        initModuleApp(this)
        initModuleData(this)
    }
}