package com.ly.share

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ly.comm.ServiceFactory
import kotlinx.android.synthetic.main.activity_share.*

@Route(path = "/share/mainAc")
class ShareAc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        val isLogin = ServiceFactory.instance.accountService?.userStringId()
        tvShareShowUser.text = isLogin.toString()
    }
}
