package com.union.bangbang.todokotlin.base.utils

import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.union.bangbang.todokotlin.R

/**
 * Rabies
 * @author USER
 * Date:   2019-01-21
 * Time:   14:20
 */
object  ArouterUtil {
    private fun build(url: String, bundle: Bundle?): Postcard {
        return ARouter.getInstance()
                .build(url)
                .withTransition(R.anim.push_right_in, R.anim.push_right_out)
                .with(bundle)
    }

    fun navigation(url: String, bundle: Bundle) {
        build(url, bundle).navigation()
    }
    fun navigation(url: String) {
        build(url, null).navigation()
    }
}