package com.king.todo.ui;

import android.app.Application;
import android.app.NotificationManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.king.todo.BuildConfig;
import com.king.todo.R;
import com.king.todo.independent.base.utils.NotifyManager;
import com.king.todo.inject.component.AppComponent;
import com.king.todo.inject.component.DaggerAppComponent;
import com.king.todo.inject.module.AppModule;
import com.king.todo.ui.user.User;
import com.king.todo.ui.user.login.LoginActivity;
import com.union.bangbang.zero.AppUtil;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.crash.CaocConfig;
import me.goldze.mvvmhabit.utils.KLog;

public class MoneyApplication extends BaseApplication {
    private static AppComponent appComponent;
    private static MoneyApplication application;
    private static User user;
    public static AppComponent getAppComponent() {
        return appComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initMvvm();
        AppUtil.getInstance().init(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
        initARouter();
        application =this;
    }

    private void initARouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
    private void initMvvm(){
        KLog.init(true);
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(LoginActivity.class) //重新启动后的activity
                //.errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
                //.eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }
}
