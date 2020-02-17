package com.ly.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseSimpleFragment : com.trello.rxlifecycle3.components.support.RxFragment() {
    private lateinit var contentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentView = inflater.inflate(getLayoutResID(), container, false)
        initPresenter()
        return contentView
    }

    abstract fun getLayoutResID(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView(savedInstanceState)
        initData(savedInstanceState)
    }

    open fun initPresenter() {

    }

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)
}