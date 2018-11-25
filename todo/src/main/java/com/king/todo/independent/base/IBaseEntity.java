package com.king.todo.independent.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.king.todo.BR;
import com.king.todo.independent.annotation.LayoutHelper;

/**
 * @name money
 * @class name：com.king.todo.independent.base
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/26 10:49 PM
 * @change
 * @chang time
 * @class describe
 */
public abstract class IBaseEntity<ViewBinding extends ViewDataBinding> {
    private transient LayoutHelper layoutHelper;
    private transient int var =BR.vm;
    private ViewBinding binding ;

    public ViewBinding attach(ViewGroup viewGroup,ViewBinding binding) {
        this.binding = bind(viewGroup,binding);
        this.binding.setVariable(var,this);
        this.binding.executePendingBindings();
        return this.binding;
//        setContentView(dataBinding.getRoot());
    }
    private ViewBinding bind(ViewGroup viewGroup,ViewBinding binding){
        if(binding==null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), getLayoutId(), viewGroup, false);
        }
        return binding;
    }
    private LayoutHelper getLayoutHelper()  {
        if(layoutHelper==null)
            layoutHelper = findModelView(this.getClass());
        if (layoutHelper==null)throw new RuntimeException("add @LayoutHelper at you Activity:"+this.getClass());
        return layoutHelper;
    }
    public   @LayoutRes
    int getLayoutId()  {
        int[] value = getLayoutHelper().value();
        int length = value.length;
        return value[getIndexOfView()];
    }
    public int getIndexOfView(){
        return 0;
    }
    /**
     * 寻找 当前类 或者父类中的注解
     * @param thisCls
     * @return
     */
    private static LayoutHelper findModelView(Class<?> thisCls) {
        if (thisCls == null) return null;
        LayoutHelper contentView = thisCls.getAnnotation(LayoutHelper.class);
        if (contentView == null) return findModelView(thisCls.getSuperclass());
        return contentView;
    }
    public void removeBind(){
        this.binding=null;
    }
}
