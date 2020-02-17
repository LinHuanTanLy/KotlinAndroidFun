package com.ly.login.manager

object UserInfoManager {

    private var userInfo: String? = null
    fun getUserName(): String = userInfo.toString()
    fun setUserName(userInfo: String?) {
        UserInfoManager.userInfo = userInfo
    }
}
