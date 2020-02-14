package com.ly.comm.net

import java.lang.RuntimeException

class ApiException(val code: Int, val msg: String) : RuntimeException()