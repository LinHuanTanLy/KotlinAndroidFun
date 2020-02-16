package com.ly.login.contract

import com.ly.base.IView
import com.ly.bean.ChaptersBean

interface ILogin {

    interface View : IView {
        fun onSuccess(list: List<ChaptersBean>)
    }
}