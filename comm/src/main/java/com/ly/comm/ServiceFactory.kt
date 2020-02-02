package com.ly.comm

import com.ly.comm.userService.IAccountService


class ServiceFactory
/**
 * 禁止外部创建 ServiceFactory 对象
 */
private constructor() {
    /**
     * 返回 Login 组件的 Service 实例
     */
    /**
     * 接收 Login 组件实现的 Service 实例
     */
    var accountService: IAccountService? = null
        get() {
            if (field == null) {
                field = EmptyAccountService()
            }
            return field
        }

    private object Inner {
        val serviceFactory = ServiceFactory()
    }

    companion object {
        /**
         * 通过静态内部类方式实现 ServiceFactory 的单例
         */
        val instance: ServiceFactory
            get() = Inner.serviceFactory
    }
}
