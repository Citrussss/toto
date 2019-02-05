package com.union.bangbang.todokotlin.base.recycle.adapter

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.IdRes
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/23 3:06 PM
 * 只有编译器可能不骗你。
 */
interface ViewSelectHelper<out B : ViewDataBinding> {
    var index: Int
    var layoutId: IntArray
    fun getLayoutId() = layoutId[getModelIndex()]
    fun getModelIndex() = index
    fun getDatabinding(context: Context, co: ViewGroup, attachToParent: Boolean) = DataBindingUtil.inflate<B>(LayoutInflater.from(context), getLayoutId(), co, attachToParent)
}
//class ViewSelectHelperImp() :ViewSelectHelper{
//
//}
