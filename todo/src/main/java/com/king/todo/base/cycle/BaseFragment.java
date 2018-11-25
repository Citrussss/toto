package com.king.todo.base.cycle;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.binding.model.cycle.DataBindingFragment;
import com.binding.model.model.ViewModel;
import com.binding.model.util.ReflectUtil;
import com.king.todo.inject.component.DaggerFragmentComponent;
import com.king.todo.inject.component.FragmentComponent;
import com.king.todo.inject.module.FragmentModule;
import com.king.todo.ui.MoneyApplication;

import java.lang.reflect.Method;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:00
 * modify developer：  admin
 * modify time：14:00
 * modify remark：
 *
 * @version 2.0
 */


public abstract class BaseFragment<VM extends ViewModel> extends DataBindingFragment<FragmentComponent> {
    @Inject
    public VM vm;
    private FragmentComponent component;

    @SuppressWarnings("unchecked")
    public final View inject(Bundle savedInstanceState, ViewGroup parent, boolean attachToParent) {
        View view;
        try {
            Method method = FragmentComponent.class.getDeclaredMethod("inject", getClass());
            ReflectUtil.invoke(method, getComponent(), this);
            view = vm.attachContainer(this,parent,attachToParent,savedInstanceState).getRoot();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(String.format("edit:%1s need to add_friends @Method inject to FragmentComponent", getClass().getSimpleName()));
        }
        return view;
    }

    @Override
    public final FragmentComponent getComponent() {
        if (component == null) {
            component = DaggerFragmentComponent.builder()
                    .appComponent(MoneyApplication.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return component;
    }

}
