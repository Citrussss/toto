package com.union.bangbang.todokotlin.base.recycle.adapter

import android.arch.lifecycle.LifecycleObserver
import android.databinding.ViewDataBinding
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.union.bangbang.todokotlin.TodoApplication
import com.union.bangbang.todokotlin.base.utils.LifecycleApiControl
import io.reactivex.disposables.Disposable


/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/3 4:02 PM
 * 只有编译器可能不骗你。
 */
abstract class ViewSelectImp<out B : ViewDataBinding> :ViewSelectHelper<B> ,LifecycleApiControl
