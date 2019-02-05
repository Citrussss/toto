package com.union.bangbang.todokotlin

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import com.union.bangbang.zero.AppUtil
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
        MultiDex.install(this);
        DaggerAppComponent.builder().application(this).build().inject(this)
        appUtil.init(this)
        Stetho.initializeWithDefaults(this);
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
