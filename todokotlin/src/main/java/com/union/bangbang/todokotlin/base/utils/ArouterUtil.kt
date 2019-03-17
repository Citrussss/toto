package com.union.bangbang.todokotlin.base.utils

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.union.bangbang.todokotlin.R

/**
 * Rabies
 * @author USER
 * Date:   2019-01-21
 * Time:   14:20
 */
object ArouterUtil {
    fun navigation(url: String, bundle: Bundle? = null, enterAnim: Int = R.anim.push_right_in, exitAnim: Int = R.anim.push_left_out, compat: ActivityOptionsCompat? = null) {
        ARouter.getInstance()
                .build(url)
                .withTransition(enterAnim, exitAnim)
                .withOptionsCompat(compat)
                .with(bundle).navigation()
    }
    /*   fun navigation(url: String) {
           build(url, null).navigation(AppUtil.peekActivity())
       }*/
}