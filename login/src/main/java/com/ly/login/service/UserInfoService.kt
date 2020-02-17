package com.ly.login.service

import com.ly.comm.userService.IAccountService
import com.ly.login.manager.UserInfoManager

class UserInfoService : IAccountService {
    override fun isLogin(): Boolean = false

    override fun userStringId(): String = UserInfoManager.getUserName()
}