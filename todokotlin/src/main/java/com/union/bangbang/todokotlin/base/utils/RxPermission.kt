package com.union.bangbang.todokotlin.base.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import com.union.bangbang.todokotlin.base.data.net.Location
import com.union.bangbang.zero.AppUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019/2/22 9:11 PM
 * 只有编译器可能不骗你。
 */
fun Fragment.request(vararg permissions: String): Observable<Permission> {
    val rxPermission = RxPermissions(this)
    return rxPermission.requestEach(*permissions).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
}

fun FragmentActivity.request(vararg permissions: String): Observable<Permission> {
    val rxPermission = RxPermissions(this)
    return rxPermission.requestEach(*permissions).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
}

fun Location.request(vararg permissions: String): Observable<Permission> {
    val rxPermission = RxPermissions(AppUtil.peekActivity())
    return rxPermission.requestEach(*permissions).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
}

fun FragmentActivity.request(consumer: Consumer<Permission>, vararg permissions: String) {
    val rxPermission = RxPermissions(this);
    rxPermission
            .requestEach(*permissions)
            .subscribe(consumer)
}