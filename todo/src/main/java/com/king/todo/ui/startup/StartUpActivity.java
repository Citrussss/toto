package com.king.todo.ui.startup;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.binding.model.Config;
import com.king.todo.BR;
import com.king.todo.R;
import com.king.todo.base.arouter.ArouterUtil;
import com.king.todo.databinding.ActivityStartupBinding;
import com.king.todo.inject.component.ActivityComponent;

import me.goldze.mvvmhabit.base.BaseActivity;

import static com.king.todo.inject.component.ActivityComponent.Router.login;

@Route(path = ActivityComponent.Router.startup)
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
        savedInstanceState.putString(Config.title,"用户登录");
        ArouterUtil.navigation(login,savedInstanceState);
        finish();
    }
}
