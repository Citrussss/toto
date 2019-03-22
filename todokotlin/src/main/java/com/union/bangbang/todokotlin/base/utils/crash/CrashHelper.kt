package com.union.bangbang.todokotlin.base.utils.crash

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import com.union.bangbang.todokotlin.base.utils.email.MailSender
import java.io.File
import java.io.PrintWriter

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/21 8:51 AM
 * 只有编译器可能不骗你。
 */
class CrashHelper : Thread.UncaughtExceptionHandler {
    lateinit var defaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler
    lateinit var context: Context
    override fun uncaughtException(t: Thread, e: Throwable) {
        val file = File("${context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}/${System.currentTimeMillis()}.log")
//        file.mkdir()
        val pw = PrintWriter(file)
        try {
            Log.v(TAG, file.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        pw.println("品牌：${Build.BRAND} 型号：${Build.MODEL} SDK: ${Build.VERSION.SDK_INT} OS:${Build.VERSION.RELEASE}")
        e.printStackTrace(pw)
        pw.flush()
        pw.close()
        MailSender().send(context.packageName, file)

//        Log.getStackTraceString(e)
        //在此处进行异常的保存或者提交，例如蒲公英
        defaultUncaughtExceptionHandler.uncaughtException(t, e)
    }

    companion object {
        private val TAG = "CrashHelper"
        private val instance: CrashHelper by lazy { CrashHelper() }
        @Synchronized
        fun init(context: Context) {
            instance.defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
            instance.context = context
            Thread.setDefaultUncaughtExceptionHandler(instance)
        }
//        @Synchronized
//        fun register(context: Context) {
//            instance = instance ?: synchronized(this) {
//                instance
//                        ?: CrashHelper(Thread.getDefaultUncaughtExceptionHandler(), context.applicationContext as Application)
//            }
//            Thread.setDefaultUncaughtExceptionHandler(instance)
//        }

    }
}