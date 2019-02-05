package com.union.bangbang.todokotlin.base.activity

import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.union.bangbang.todokotlin.BR
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import java.util.function.Consumer

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/16 3:45 PM
 * 只有编译器可能不骗你。
 */
abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: Binding
    private val rxList: ArrayList<Disposable> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewDataBinding()
    }

    companion object Route {
        fun onStartActivity(context: Context, clazz: Class<*>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }

    fun initViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.setVariable(BR.vm, initViewModel())
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): AndroidViewModel?
    fun addDisposable(disposable: Disposable) {
        rxList.add(disposable)
    }

    fun stopAllDisposable() {
        rxList.forEach({ it.dispose() })
    }

    override fun onDestroy() {
        stopAllDisposable()
        super.onDestroy()
    }
}