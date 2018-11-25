package com.union.bangbang.testapp;

import android.app.Application;
import com.union.bangbang.zero.AppUtil;

/**
 * @name zero
 * @anthor bangbang QQ:740090077
 * @time 2018/11/1 8:21 PM
 * 只有编译器可能不骗你。
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.getInstance().init(this);
    }
}
