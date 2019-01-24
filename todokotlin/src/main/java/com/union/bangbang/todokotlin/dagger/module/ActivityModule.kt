package com.union.bangbang.todokotlin.dagger.module

import com.union.bangbang.todokotlin.BuildConfig
import com.union.bangbang.todokotlin.dagger.annotation.ActivityScope
import com.union.bangbang.todokotlin.ui.startup.StartUpActivity
import com.union.bangbang.todokotlin.ui.user.UserListActivity
import com.union.bangbang.todokotlin.ui.user.login.LoginActivity
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

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeUserListActivityInjector(): UserListActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeLoginActivityInjecttor(): LoginActivity

    companion object {
        const val app ="/todo/"
        const val user = app+"user/"
        const val user_login= app+"login"
    }
}