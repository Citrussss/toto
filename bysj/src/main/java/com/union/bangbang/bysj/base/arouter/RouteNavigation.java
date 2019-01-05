package com.union.bangbang.bysj.base.arouter;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019/1/5 4:08 PM
 * 只有编译器可能不骗你。
 */
public class RouteNavigation {
    public static void Navigation(String path){
        ARouter.getInstance().build(path).navigation();
    }
    public interface Router{
        String home="/home/";
        String user="/user/";
        String startup = home+"startup";
        String login = user+"login";
    }
}
