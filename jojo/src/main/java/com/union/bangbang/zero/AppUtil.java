package com.union.bangbang.zero;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.Stack;

/**
 * @name zero
 * @class name：com.union.bangbang.zero.util
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/24 9:26 PM
 * @change
 * @chang time
 * @class describe
 */
public class AppUtil implements Application.ActivityLifecycleCallbacks {
    private static final AppUtil manage = new AppUtil();
    private static Stack<FragmentActivity> activityStack = new Stack<>();

    private AppUtil() {
    }

    public static AppUtil getInstance() {
        return manage;
    }

    public void init(Application application) {
        application.registerActivityLifecycleCallbacks(manage);
    }

    public static Stack<FragmentActivity> getActivityStack() {
        return activityStack;
    }

    /**
     * @return 返回栈顶的 @ Activity activity
     */
    public static FragmentActivity peekActivity() {
        try {
            return activityStack.peek();
        } catch (Exception e) {
            throw new RuntimeException("没有在主程序中添加监听：请在主程序中调用init方法:" + e.toString());
        }
    }

    public static void finishAll() {
        while (activityStack.peek() != null) {
            activityStack.pop().finish();
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof FragmentActivity) {
            activityStack.add((FragmentActivity) activity);
        } else {
            throw new RuntimeException("请使用FragmentActivity进行项目搭建");
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityStack.remove(activity);
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return peekActivity().getDrawable(id);
        } else {
            return peekActivity().getResources().getDrawable(id);
        }
    }

//    public boolean saveValue(String key,Object value){
//
//    }
}
