package com.ly.comm.net

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class Api private constructor() {

    lateinit var retofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    private object Helper {
        val instance = Api()
    }

    companion object {
        fun getInstance() = Helper.instance
        const val HOST = "https://www.wanandroid.com"
    }

    fun init(context: Context) {

    }

    private fun initRetrofit() {
        retofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(HOST)
            .addConverterFactory(CxConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    private  fun initOkHttpClient(context: Context){

    }
}