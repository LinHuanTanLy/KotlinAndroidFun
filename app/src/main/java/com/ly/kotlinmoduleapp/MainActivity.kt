package com.ly.kotlinmoduleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        tvMainToLogin.setOnClickListener {
            ARouter.getInstance().build("/login/ac").navigation()
        }
        tvMainToShare.setOnClickListener {
            ARouter.getInstance().build("/share/mainAc").navigation()
//            ARouter.getInstance().build("/share/ac").navigation()
        }
    }
}
