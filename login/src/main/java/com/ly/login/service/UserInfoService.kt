package com.ly.login.service

import com.ly.comm.userService.IAccountService

class UserInfoService : IAccountService {
    override fun isLogin(): Boolean = false

    override fun userStringId(): String = "ly"
}