package com.union.bangbang.todokotlin.base.utils.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/15 9:46 PM
 * 只有编译器可能不骗你。
 */

@BindingAdapter("android:src")
fun ImageView.setSrc(url: String?) {
    Glide.with(this.context).load(url).into(this)
}
