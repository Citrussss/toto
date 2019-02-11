package com.union.bangbang.todokotlin.base.data.model

import com.union.bangbang.todokotlin.BuildConfig
import com.union.bangbang.todokotlin.base.data.net.Api
import com.union.bangbang.todokotlin.base.data.pojo.User
import com.union.bangbang.todokotlin.base.data.pojo.UserEntity
import io.reactivex.Observable
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
}
