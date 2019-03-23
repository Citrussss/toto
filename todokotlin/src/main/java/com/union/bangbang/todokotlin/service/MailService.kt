package com.union.bangbang.todokotlin.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.union.bangbang.todokotlin.base.utils.email.MailSender
import java.io.File

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/23 9:43 PM
 * 只有编译器可能不骗你。
 */
class MailService : IntentService("Mail") {


    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val title = intent.getStringExtra("title")
            val content = intent.getStringExtra("content")
            val path = intent.getStringExtra("path")
            try {
                if (!TextUtils.isEmpty(path) && File(path).exists()) {
                    MailSender().send(this.packageName + title, File(path));
                } else if (!TextUtils.isEmpty(content)) {
                    MailSender().send(this.packageName + title, content)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        fun start(context: Context, title: String = "", content: String = "", path: String = "") {
            val intent = Intent(context, MailService::class.java)
            intent.putExtra("title", title)
            intent.putExtra("content", content)
            intent.putExtra("path", path)
            context.startService(intent)
        }
    }
}