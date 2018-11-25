package com.king.todo.base.utils;

import android.os.Looper;

import com.sdsmdg.tastytoast.TastyToast;
import com.union.bangbang.zero.AppUtil;

/**
 * @name money
 * @class name：com.king.todo.base.utils
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/24 10:06 PM
 * @change
 * @chang time
 * @class describe
 */
public class FunnyToast extends TastyToast {
    public static void error(Throwable e) {
        new Thread() {
            public void run() {
                Looper.prepare();
                makeText(AppUtil.peekActivity(), e.getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
                Looper.loop();
            }
        }.start();
    }
    public static void message(Object o) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                TastyToast.makeText(AppUtil.peekActivity(), o.toString(), TastyToast.LENGTH_LONG, TastyToast.INFO);
                Looper.loop();
            }
        }.start();
    }
}
