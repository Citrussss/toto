package com.union.bangbang.todokotlin.base.utils

import android.os.Build
import android.os.Bundle
import android.support.annotation.StringDef
import android.support.v4.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.union.bangbang.todokotlin.Constants
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.utils.TransitionTypeValue.explode
import com.union.bangbang.todokotlin.base.utils.TransitionTypeValue.fade
import com.union.bangbang.todokotlin.base.utils.TransitionTypeValue.slide
import com.union.bangbang.zero.AppUtil

/**
 * Rabies
 * @author USER
 * Date:   2019-01-21
 * Time:   14:20
 */
object ArouterUtil {
    fun navigation(url: String, bundle: Bundle = Bundle(), enterAnim: Int = R.anim.push_right_in, exitAnim: Int = R.anim.push_left_out
                   , @TransitionType transitionType: String = slide) {
        var animation: ActivityOptionsCompat? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animation = when (transitionType) {
                explode -> ActivityOptionsCompat.makeSceneTransitionAnimation(AppUtil.peekActivity())
                fade -> ActivityOptionsCompat.makeSceneTransitionAnimation(AppUtil.peekActivity())
                slide -> ActivityOptionsCompat.makeSceneTransitionAnimation(AppUtil.peekActivity())
                else -> null
            }
            bundle.putString(Constants.Transition_Type, transitionType)
        }
        ARouter.getInstance()
                .build(url)
//                .withTransition(enterAnim, exitAnim)
                .withOptionsCompat(animation)
                .with(bundle).navigation(AppUtil.peekActivity())
    }
}

@Retention(AnnotationRetention.SOURCE)
@StringDef(explode, slide, fade)
annotation class TransitionType

object TransitionTypeValue {
    const val explode = "explode"
    const val slide = "slide"
    const val fade = "fade"
}