package com.king.todo.independent.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.todo.BR;
import com.king.todo.independent.annotation.LayoutHelper;
import com.union.bangbang.zero.AppUtil;

import razerdp.basepopup.BasePopupWindow;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/2 10:01 PM
 * 只有编译器可能不骗你。
 */


public class IBasePopup<ViewBinding extends ViewDataBinding> extends BasePopupWindow {
    private transient LayoutHelper layoutHelper;
    private transient ViewBinding binding;
    private transient int var =BR.vm;
    public IBasePopup(Context context) {
         super(context);
    }
    public ViewBinding attach(ViewGroup viewGroup, ViewBinding binding) {
        if(binding==null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup!=null?viewGroup.getContext():AppUtil.peekActivity()), getLayoutId(), null, false);
        }
        this.binding = binding;
        this.binding.setVariable(var,this);
        this.binding.executePendingBindings();
        return this.binding;
    }
    @Override
    public View onCreateContentView() {
        return getBinding().getRoot();
    }

    public @LayoutRes
    int getLayoutId() {
        int[] value = getLayoutHelper().value();
        int length = value.length;
        return value[getIndexOfView()];
    }
    private LayoutHelper getLayoutHelper()  {
        if(layoutHelper==null)
            layoutHelper = findModelView(this.getClass());
        if (layoutHelper==null)throw new RuntimeException("add @LayoutHelper at you Activity:"+this.getClass());
        return layoutHelper;
    }
    private static LayoutHelper findModelView(Class<?> thisCls) {
        if (thisCls == null) return null;
        LayoutHelper contentView = thisCls.getAnnotation(LayoutHelper.class);
        if (contentView == null) return findModelView(thisCls.getSuperclass());
        return contentView;
    }
    public int getIndexOfView(){
        return 0;
    }

    public ViewBinding getBinding() {
        if(binding==null)attach(null,null);
        return binding;
    }
}
