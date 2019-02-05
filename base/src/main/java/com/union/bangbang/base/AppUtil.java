package com.union.bangbang.base;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.DrawableRes;

import java.util.Stack;

/**
 * 不乱于心，不困于情。不畏将来，不念过往。如此，安好!
 * <p>
 * 深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!
 * <p>
 * 善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!
 * <p>
 * 勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!
 * <p>
 * 无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
public class AppUtil implements Application.ActivityLifecycleCallbacks {
    private static final AppUtil manage = new AppUtil();
    private static Stack<Activity> activityStack = new Stack<>();

    private AppUtil() {
    }

    public static AppUtil getInstance() {
        return manage;
    }

    public void init(Application application) {
        application.registerActivityLifecycleCallbacks(manage);
    }

    /**
     * @return 返回栈顶的 @ Activity activity
     */
    public static Activity peekActivity() {
        try {
            return activityStack.peek();
        } catch (Exception e) {
            throw new RuntimeException("没有在主程序中添加监听：请在主程序中调用init方法:" + e.toString());
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityStack.add(activity);
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
}
