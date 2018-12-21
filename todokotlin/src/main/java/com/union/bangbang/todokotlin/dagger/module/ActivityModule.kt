package com.union.bangbang.todokotlin.dagger.module

import com.union.bangbang.todokotlin.dagger.annotation.ActivityScope
import com.union.bangbang.todokotlin.ui.startup.StartUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 页面描述：ActivityModule
 *
 * Created by ditclear on 2018/6/25.
 */
@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeStartUpActivityInjector(): StartUpActivity

}