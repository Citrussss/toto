package com.union.bangbang.bysj.ui.user.login;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.union.bangbang.bysj.BR;
import com.union.bangbang.bysj.R;
import com.union.bangbang.bysj.base.arouter.RouteNavigation;
import com.union.bangbang.bysj.databinding.ActivityLoginBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019/1/5 4:13 PM
 * 只有编译器可能不骗你。
 */
@Route(path = RouteNavigation.Router.login)
public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }
}
