package com.ly.kotlinmoduleapp.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
        ARouter.openLog()
        ARouter.openDebug()
    }
}