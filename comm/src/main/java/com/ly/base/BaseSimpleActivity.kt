package com.ly.base

import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.ly.comm.R
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

abstract class BaseSimpleActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initP()
        initImmersionBar()
        initView(savedInstanceState)
        initData(savedInstanceState)
    }

    abstract fun getContentView(): Int
    abstract fun initData(savedInstanceState: Bundle?)
    abstract fun initView(savedInstanceState: Bundle?)
    open fun initP() {}
    private fun initImmersionBar() {
        immersionBar {
            barColor(R.color.colorPrimary)
        }
    }

}