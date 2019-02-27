package com.union.bangbang.todokotlin.dagger.module

import android.support.v7.widget.RecyclerView
import com.union.bangbang.todokotlin.dagger.annotation.ActivityScope
import com.union.bangbang.todokotlin.ui.about.AboutActivity
import com.union.bangbang.todokotlin.ui.home.HomeActivity
import com.union.bangbang.todokotlin.ui.startup.StartUpActivity
import com.union.bangbang.todokotlin.ui.user.UserListActivity
import com.union.bangbang.todokotlin.ui.user.login.LoginActivity
import com.union.bangbang.todokotlin.ui.user.password.EditPwdActivity
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
    abstract fun bindRegisterActivity(): RegisterActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindEditPwdActivity(): EditPwdActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindAboutActivity(): AboutActivity
    companion object {
        const val app = "/todo/"
        const val user = app + "user/"
        const val home = app + "home/"
        const val about = app +"about"

        const val user_login = user + "login"
        const val user_register = user + "register"
        const val user_pwd = user + "pwd"

        const val home_page = home + "page"
    }
}