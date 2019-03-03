package com.union.bangbang.todokotlin.base.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposable

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/3 4:07 PM
 * 只有编译器可能不骗你。
 */
interface LifecycleApiControl : LifecycleObserver{

    private val rxList: ArrayList<Disposable>
        get() = ArrayList()

    fun addDisposable(disposable: Disposable) {
        rxList.add(disposable)
    }

    fun stopAllDisposable() {
        rxList.forEach { it.dispose() }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {

    }
}