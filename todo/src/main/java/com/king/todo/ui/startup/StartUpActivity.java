package com.king.todo.ui.startup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.king.todo.base.cycle.BaseActivity;
import com.king.todo.inject.component.ActivityComponent;

@Route(path = ActivityComponent.Router.startup)
public class StartUpActivity extends BaseActivity<StartUpModel> {
}
