package com.union.bangbang.todokotlin.base.data.net

import com.union.bangbang.todokotlin.base.data.pojo.InfoEntity
import com.union.bangbang.todokotlin.base.data.pojo.bing.BingWallpageEntity
import com.union.bangbang.zero.util.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/15 8:58 PM
 * 只有编译器可能不骗你。
 */
interface BingApi {

    //获取bing壁纸 ?format=js&idx=0&n=1
    @GET("HPImageArchive.aspx")
    fun getWallpaper(@Query("format") format: String, @Query("idx") idx: Int, @Query("n") n: Int):Observable<BingWallpageEntity>
}