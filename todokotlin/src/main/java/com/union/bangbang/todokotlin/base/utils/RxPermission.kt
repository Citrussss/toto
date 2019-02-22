package com.union.bangbang.todokotlin.base.utils

import android.support.v4.app.FragmentActivity
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import com.union.bangbang.todokotlin.base.data.net.Location
import com.union.bangbang.zero.AppUtil
import io.reactivex.functions.Consumer

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019/2/22 9:11 PM
 * 只有编译器可能不骗你。
 */
fun Location.request(consumer: Consumer<Permission>, vararg permissions: String) {
    val rxPermission = RxPermissions(AppUtil.peekActivity());
    rxPermission
            .requestEach(*permissions)
            .subscribe(consumer);
}
 fun FragmentActivity.request(consumer: Consumer<Permission>, vararg permissions: String) {
    val rxPermission = RxPermissions(this);
    rxPermission
            .requestEach(*permissions)
            .subscribe(consumer);
}