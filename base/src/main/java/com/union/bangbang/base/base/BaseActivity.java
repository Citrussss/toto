package com.union.bangbang.base.base;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019-02-05 16:17
 * 只有编译器可能不骗你。
 */

public abstract class BaseActivity<Binding extends ViewDataBinding> extends DaggerAppCompatActivity {
    protected Binding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    protected abstract  int getLayoutId();

    public Binding getBinding() {
        if(binding==null)initView();
        return binding;
    }
}
