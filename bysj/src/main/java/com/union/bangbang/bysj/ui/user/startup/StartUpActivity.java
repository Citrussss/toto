package com.union.bangbang.bysj.ui.user.startup;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.union.bangbang.bysj.BR;
import com.union.bangbang.bysj.R;
import com.union.bangbang.bysj.base.arouter.RouteNavigation;
import com.union.bangbang.bysj.databinding.ActivityStartupBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

import static com.union.bangbang.bysj.base.arouter.RouteNavigation.Router.login;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019/1/5 3:56 PM
 * 只有编译器可能不骗你。
 */
@Route(path = RouteNavigation.Router.startup)
public class StartUpActivity extends BaseActivity<ActivityStartupBinding,StartUpModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_startup;
    }
    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RouteNavigation.Navigation(login);
    }

}
