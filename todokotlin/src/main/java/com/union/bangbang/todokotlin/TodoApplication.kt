package com.union.bangbang.todokotlin

import android.app.Application
import com.union.bangbang.zero.AppUtil

/**
 * @name zero
 * @class name：com.union.bangbang.todokotlin
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/25 9:05 PM
 * @change
 * @chang time
 * @class describe
 */
class TodoApplication : Application() {
    val appUtil = AppUtil.getInstance();
    override fun onCreate() {
        super.onCreate()
        appUtil.init(this)
    }
}
