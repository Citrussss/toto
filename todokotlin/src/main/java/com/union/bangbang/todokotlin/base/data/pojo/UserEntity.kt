package com.union.bangbang.todokotlin.base.data.pojo

import androidx.databinding.ViewDataBinding
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.recycle.adapter.ViewSelectHelper

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/21 10:47 PM
 * 只有编译器可能不骗你。
 */

data class InfoEntity<T>(
        val code: Int,
        val `data`: T
)

data class TouristEntity(
        val token: Token,
        val user: User
)

data class Token(
        val id: Int,
        val token: String,
        val userEntity: User
)

data class User(var id: Int, val name: String) : ViewSelectHelper<ViewDataBinding> {
    override var index: Int
        get() = 0 //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var layoutId: IntArray
        get() = intArrayOf(R.layout.holder_user)
        set(value) {}
}