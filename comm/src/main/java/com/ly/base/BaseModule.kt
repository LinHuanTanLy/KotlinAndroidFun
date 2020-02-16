package com.ly.base

import com.ly.comm.net.api.ApiService

abstract class BaseModule<T : ApiService> : IModule {

    val api: T
        get() = createApiService()

    abstract fun createApiService(): T
}