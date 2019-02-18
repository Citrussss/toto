package com.union.bangbang.todokotlin.dagger.module

import com.union.bangbang.todokotlin.BuildConfig
import com.union.bangbang.todokotlin.dagger.annotation.ActivityScope
import com.union.bangbang.todokotlin.ui.home.HomeActivity
import com.union.bangbang.todokotlin.ui.startup.StartUpActivity
import com.union.bangbang.todokotlin.ui.user.UserListActivity
import com.union.bangbang.todokotlin.ui.user.login.LoginActivity
import com.union.bangbang.todokotlin.ui.user.register.RegisterActivity
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

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeHomeActivityInjecttor(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindRegisterActivity() : RegisterActivity
    companion object {
        const val app ="/todo/"
        const val user = app+"user/"
        const val home = app+"home/"

        const val user_login= app+"login"
        const val user_register=app+"register"

        const val home_page = home+"page"
    }
}