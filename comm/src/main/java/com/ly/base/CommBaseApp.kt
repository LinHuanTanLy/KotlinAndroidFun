package com.ly.base

import com.ly.comm.net.Api

abstract class CommBaseApp : BaseApp()  {

    override fun onCreate() {
        super.onCreate()
        Api.getInstance().init(this)
    }
}