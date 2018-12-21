package com.union.bangbang.todokotlin.base.data.model

import com.union.bangbang.todokotlin.base.data.net.Api
import javax.inject.Inject

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/21 10:56 PM
 * 只有编译器可能不骗你。
 */
class DataService @Inject constructor(private val net: Api) {
    fun tourist() = net.tourist()
}
