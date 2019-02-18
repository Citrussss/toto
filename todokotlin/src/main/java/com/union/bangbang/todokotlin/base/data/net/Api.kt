package com.union.bangbang.todokotlin.base.data.net

import com.union.bangbang.todokotlin.base.data.pojo.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
    fun tourist(): Observable<InfoEntity<TouristEntity>>

    @GET("user/findAll")
    fun userFindAll(): Observable<InfoEntity<List<User>>>

    @POST("user/login")
    fun login(@Body user: UserEntity): Observable<InfoEntity<LoginEntity>>

    @POST("user/register")
    fun register(@Body user: UserEntity): Observable<InfoEntity<String>>

    @POST("memo/add")
    fun addMemo(@Body memo: Memo): Observable<InfoEntity<Memo>>

    @GET("memo/findMemoByLocation")
    fun findMemoByLocation(@Query("longitude") longitude: Float, @Query("latitude") latitude: Float, @Query("distance") distance: Float): Observable<InfoEntity<Memo>>

}