package com.union.bangbang.todokotlin.base.okhttp

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/16 4:22 PM
 * 只有编译器可能不骗你。
 */
class ApiException(val code: Int,val  msg: String) : Exception()