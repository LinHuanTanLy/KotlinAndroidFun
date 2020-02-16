package com.ly.comm.net.api

import com.ly.comm.net.Api

object ApiServiceUtil {
    inline fun <reified T> getApiService(): T {
        return Api.getInstance().retofit.create(T::class.java)
    }
}