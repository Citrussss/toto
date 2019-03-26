package com.union.bangbang.todokotlin.base.data.pojo

import android.databinding.ViewDataBinding
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.recycle.adapter.ViewSelectHelper

data class User(
        val id: Int,
        val mobile: Long,
        val password: String,
        val name:String
) : ViewSelectHelper<ViewDataBinding> {
    override var index: Int
        get() = 0
        set(value) {}
    override var layoutId: IntArray
        get() = intArrayOf(R.layout.holder_user)
        set(value) {}
}