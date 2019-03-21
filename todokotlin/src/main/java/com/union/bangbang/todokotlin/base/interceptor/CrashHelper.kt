package com.union.bangbang.todokotlin.base.interceptor

import android.app.Application
import android.content.Context
import android.util.Log
import java.lang.Exception

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/21 8:51 AM
 * 只有编译器可能不骗你。
 */
class CrashHelper private constructor(private var defaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler, private var application: Application) : Thread.UncaughtExceptionHandler {
    init {
        instance = this
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        Log.w(t.toString(), e.toString())
        //在此处进行异常的保存或者提交，例如蒲公英
        defaultUncaughtExceptionHandler.uncaughtException(t, e)

    }

    companion object {
        private val TAG = "CrashHelper"
        private var instance: CrashHelper? = null
            get() {
                return field ?:throw Exception("un register CrashHelper")
            }
        @Synchronized
        fun register(context: Context) {
            instance = instance ?: synchronized(this) {
                instance
                        ?: CrashHelper(Thread.getDefaultUncaughtExceptionHandler(), context.applicationContext as Application)
            }
            Thread.setDefaultUncaughtExceptionHandler(instance)
        }

    }
}