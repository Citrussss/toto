package com.king.todo.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.view.swipe.SwipeBackLayout;
import com.king.todo.base.cycle.BaseActivity;
import com.king.todo.inject.component.ActivityComponent;


@Route(path = ActivityComponent.Router.home)
public class HomeActivity extends BaseActivity<HomeModel> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int isSwipe() {
        return SwipeBackLayout.FROM_NO;
    }
}
