package com.ly.comm.net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

internal class CxResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) :
    Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val response = value.string()
        val basicResponse: BasicResponse<*> = gson.fromJson(response, BasicResponse::class.java)
        // 这里只是为了检测code是否!=1,所以只解析HttpStatus中的字段,因为只要code和message就可以了
        if (basicResponse.errorCode != BasicResponseCode.SUCCESS) {
            value.close()
            throw ApiException(basicResponse.errorCode, basicResponse.errorMsg)
        }
        return value.use {
            adapter.fromJson(response)
        }
    }

}
