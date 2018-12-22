package com.union.bangbang.todokotlin.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.ui.startup.StartUpModel
import com.union.bangbang.todokotlin.ui.user.UserListActivity
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/16 3:45 PM
 * 只有编译器可能不骗你。
 */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var s: String
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    companion object Route {
        fun onStartActivity(context: Context, clazz: Class<*>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }
}