package com.union.bangbang.todokotlin

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.union.bangbang.todokotlin.dagger.module.AppModule
import com.union.bangbang.zero.AppUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @name zero
 * @class name：com.union.bangbang.todokotlin
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/25 9:05 PM
 * @change
 * @chang time
 * @class describe
 */
class TodoApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector


    val appUtil = AppUtil.getInstance();
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        appUtil.init(this)
        Stetho.initializeWithDefaults(this);

    }
}
