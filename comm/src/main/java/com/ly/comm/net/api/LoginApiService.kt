package com.ly.comm.net.api

import com.ly.bean.ChaptersBean
import com.ly.comm.net.BasicResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface LoginApiService: ApiService {
    @GET("/wxarticle/chapters/json")
    fun getChapters(): Observable<BasicResponse<List<ChaptersBean>>>
}