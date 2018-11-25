package com.king.todo.inject.component;


import com.king.todo.inject.module.ActivityModule;
import com.king.todo.inject.scope.ActivityScope;
import com.king.todo.ui.home.HomeActivity;
import com.king.todo.ui.startup.StartUpActivity;
import com.king.todo.ui.user.login.LoginActivity;
import com.king.todo.ui.user.register.RegisterActivity;

import dagger.Component;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:28
 * modify developer：  admin
 * modify time：14:28
 * modify remark：
 *
 * @version 2.0
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(StartUpActivity activity);
    void inject(HomeActivity activity);
    void inject(LoginActivity activity);
    void inject(RegisterActivity activity);
    interface Router {
        String main = "/main/";
        String user = "/user/";
        String startup = main + "startup";
        String home = main + "home_navigation";
        String login = user+"login";
        String rigister = user +"register";
        String image = main +"image";
//        String sensor = main +"sensor";
    }
}
