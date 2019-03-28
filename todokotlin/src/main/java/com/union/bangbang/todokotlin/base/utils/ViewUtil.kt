package com.union.bangbang.todokotlin.base.utils

import android.os.Build
import android.support.annotation.ColorRes
import android.view.View

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/28 7:51 PM
 * 沉着冷静面对⛈️
 */
fun View.getColor(@ColorRes i: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.context.getColor(i)
    } else {
        this.context.resources.getColor(i)
    }
}