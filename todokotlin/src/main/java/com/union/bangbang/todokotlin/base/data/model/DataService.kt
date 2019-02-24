package com.union.bangbang.todokotlin.base.data.model

import com.union.bangbang.todokotlin.BuildConfig
import com.union.bangbang.todokotlin.base.data.net.Api
import com.union.bangbang.todokotlin.base.data.pojo.Memo
import com.union.bangbang.todokotlin.base.data.pojo.User
import com.union.bangbang.todokotlin.base.data.pojo.UserEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/21 10:56 PM
 * 只有编译器可能不骗你。
 */
class DataService @Inject constructor(private val net: Api) {
    fun tourist() = net.tourist()
    fun userList() = if (BuildConfig.DEBUG) getDefList() else net.userFindAll().map { it.data }
    fun getDefList() = Observable.range(0, 100).map { User(it, 17857025659,"tony") }
            .toList().toObservable()
    fun login(user: UserEntity) = net.login(user).subscribeOn(Schedulers.io())
    fun register(user: UserEntity)=net.register(user).subscribeOn(Schedulers.io())
    fun addMemo(memo: Memo) = net.addMemo(memo).subscribeOn(Schedulers.io())
    fun findMemoByLocation(longitude:Double,latitude:Double,distance:Double) = net.findMemoByLocation(15F,15F,100.0F).subscribeOn(Schedulers.io())

}
