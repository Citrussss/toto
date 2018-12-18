package com.union.bangbang.todokotlin.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.lang.reflect.ParameterizedType

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/16 3:45 PM
 * 只有编译器可能不骗你。
 */
abstract class BaseActivity<vm : BaseModel> : AppCompatActivity() {
    protected lateinit var viewModel: vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(viewModel::class.java)
    }

    abstract fun getLayoutId()

    fun <T> getT(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                    .genericSuperclass as ParameterizedType).getActualTypeArguments()[i] as Class<T>)
                    .newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }
}