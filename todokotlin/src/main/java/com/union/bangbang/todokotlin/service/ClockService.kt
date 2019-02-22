package com.union.bangbang.todokotlin.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.union.bangbang.todokotlin.service.ClockService.IncomingHandler
import android.os.Messenger



/**
 * Rabies
 * @author USER
 * Date:   2019-02-22
 * Time:   16:07
 */
class ClockService : Service() {


    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     * 如果不是通过绑定的方式，则这个方法可以为空
     * @param intent
     * @return
     */
    override fun onBind(intent: Intent?): IBinder? = mMessenger.getBinder();



    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    override fun onCreate() {
        println("onCreate invoke")
        super.onCreate()
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        println("onStartCommand invoke")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 服务销毁时的回调
     */
    override fun onDestroy() {
        println("onDestroy invoke")
        super.onDestroy()
    }

    /** 以下实验跨进程通讯 */
    val MSG_SAY_HELLO = 1
    private val TAG = "跨进程通讯"
    /**
     * 用于接收从客户端传递过来的数据
     */
    internal inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO -> Log.i(TAG, "thanks,Service had receiver message from client!")
                else -> super.handleMessage(msg)
            }
        }
    }
    val mMessenger = Messenger(IncomingHandler())
}