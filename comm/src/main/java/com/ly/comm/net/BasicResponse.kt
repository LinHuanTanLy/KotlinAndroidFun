package com.ly.comm.net

class BasicResponse<T> {
    var errorCode: Int = 0
    var errorMsg: String = ""
    var data: T? = null
}