package com.union.bangbang.todokotlin.base.utils.crash

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import com.union.bangbang.todokotlin.service.MailService
import org.apache.commons.lang3.time.DateFormatUtils
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
    val path: String by lazy {
        "${context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}/"
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val file = File(path + "${System.currentTimeMillis()}.log")
//        file.mkdir()
        val pw = PrintWriter(file)
        try {
            Log.v(TAG, file.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        pw.println("品牌：${Build.BRAND} 型号：${Build.MODEL} SDK: ${Build.VERSION.SDK_INT} OS:${Build.VERSION.RELEASE} 错误时间：${DateFormatUtils.format(System.currentTimeMillis(),"yyyy.MM.dd - HH:mm")}" )
        e.printStackTrace(pw)
        pw.flush()
        pw.close()
//        Log.getStackTraceString(e)
        //在此处进行异常的保存或者提交，例如蒲公英
        defaultUncaughtExceptionHandler.uncaughtException(t, e)
    }

    companion object {
        private const val TAG = "CrashHelper"
        private val instance: CrashHelper by lazy { CrashHelper() }
        @Synchronized
        fun init(context: Context) {
            instance.defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
            instance.context = context
            upload()
            Thread.setDefaultUncaughtExceptionHandler(instance)
        }

        private fun upload() {
            val fileDir = File(instance.path)
            fileDir.isDirectory.let {
                fileDir.list().forEach {
                    MailService.start(instance.context, "报错日志", path = fileDir.absolutePath+"/"+it)
                }
            }
//

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
