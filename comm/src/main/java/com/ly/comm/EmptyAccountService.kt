package com.ly.comm

import com.ly.comm.userService.IAccountService

class EmptyAccountService : IAccountService {
    override fun isLogin() = false

    override fun userStringId() = "empty"
}