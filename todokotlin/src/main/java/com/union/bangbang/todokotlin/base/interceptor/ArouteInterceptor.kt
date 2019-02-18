package com.union.bangbang.todokotlin.base.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 * Rabies
 * @author USER
 * Date:   2019-02-18
 * Time:   16:13
 */
class ArouteInterceptor() : IInterceptor {
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        callback?.onContinue(postcard);  // 处理完成，交还控制权
    }

    override fun init(context: Context?) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }
}