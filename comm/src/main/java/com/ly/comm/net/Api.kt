package com.ly.comm.net

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.orhanobut.logger.Logger
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

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
        initOkHttpClient(context)
        initRetrofit()
    }

    private fun initRetrofit() {
        retofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(HOST)
            .addConverterFactory(CxConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun initOkHttpClient(context: Context) {
        val cache = Cache(File(CacheUtils.getCacheDir(context), "HttpCache"), 1024 * 1024 * 80)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

    }


    /**
     * 打印JSON的拦截器
     */
    private var loggingInterceptor: Interceptor = invoke {
        val request = it.request()
        val requestBuffer = Buffer()
        if (request.body != null) {
            request.body!!.writeTo(requestBuffer)
        } else {
            LogUtils.d("request.body() == null")
        }
        //打印url信息
        val response = it.proceed(request)
        //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
        val responseBody = response.body
        //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
        val source = responseBody!!.source()
        source.request(Long.MAX_VALUE) // Buffer the entire body.
        //获得返回的数据
        val buffer = source.buffer
        //使用前clone()下，避免直接消耗
        Logger.json(buffer.clone().readString(Charset.forName("UTF-8")))

        response
    }
}