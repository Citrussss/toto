package com.union.bangbang.todokotlin.base.data.model

import com.union.bangbang.todokotlin.BuildConfig
import com.union.bangbang.todokotlin.base.data.net.Api
import com.union.bangbang.todokotlin.base.data.net.BingApi
import com.union.bangbang.todokotlin.base.data.pojo.*
import com.union.bangbang.todokotlin.base.okhttp.ErrorTransform
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/21 10:56 PM
 * 只有编译器可能不骗你。
 */
class DataService @Inject constructor(private val net: Api, private val bingApi: BingApi) {
    fun tourist() = net.tourist()
    fun userList() = if (BuildConfig.DEBUG) getDefList() else net.userFindAll().map { it.data }
    fun getDefList() = Observable.range(0, 100).map { User(it, 17857025659, "tony") }
            .toList().toObservable()

    fun login(user: UserEntity) = net.login(user).subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<LoginEntity>>())
    fun register(user: UserEntity) = net.register(user).subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<String>>())
    fun addMemo(memo: Memo) = net.addMemo(memo).subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<Memo>>())
    fun findMemoByLocation(longitude: Double, latitude: Double, distance: Double) = net.findMemoByLocation(longitude, latitude, distance).subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<List<Memo>>>())
    fun editPwd(user: UserEntity) = net.editPwd(user).subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<LoginEntity>>())

    fun addCollect(vararg memoIds: Long) = net.addCollect(*memoIds).subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<Boolean>>())
    fun deleteCollect(vararg collectIds: Long) = net.deleteCollect(*collectIds).subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<Boolean>>())
    fun findAllCollect() = net.findAllCollect().subscribeOn(Schedulers.io()).compose(ErrorTransform<InfoEntity<Boolean>>())
    fun getWallpaper(format: String, idx: Int, n: Int) = bingApi.getWallpaper(format, idx, n).subscribeOn(Schedulers.io())

}
