package com.union.bangbang.todokotlin.base.data.net

import com.union.bangbang.todokotlin.base.data.pojo.InfoEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/21 10:40 PM
 * 只有编译器可能不骗你。
 */
interface Api {
    /**
     * 游客登录
     */
    @GET("user/tourist")
    fun tourist():Observable<InfoEntity>
}