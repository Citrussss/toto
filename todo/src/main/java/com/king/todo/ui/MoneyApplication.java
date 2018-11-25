package com.king.todo.ui;

import android.app.Application;
import android.app.NotificationManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.king.todo.BuildConfig;
import com.king.todo.independent.base.utils.NotifyManager;
import com.king.todo.inject.component.AppComponent;
import com.king.todo.inject.component.DaggerAppComponent;
import com.king.todo.inject.module.AppModule;
import com.king.todo.ui.user.User;
import com.union.bangbang.zero.AppUtil;

public class MoneyApplication extends Application {
    private static AppComponent appComponent;
    private static MoneyApplication application;
    private static User user;
    public static AppComponent getAppComponent() {
        return appComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
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
}
