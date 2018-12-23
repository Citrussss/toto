package com.union.bangbang.todokotlin.base

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/16 3:45 PM
 * 只有编译器可能不骗你。
 */
abstract class BaseActivity<Binding:ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    companion object Route {
        fun onStartActivity(context: Context, clazz: Class<*>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }
    abstract fun getLayoutId():Int
}