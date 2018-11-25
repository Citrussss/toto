package com.king.todo.independent.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

import com.king.todo.BR;
import com.king.todo.independent.annotation.LayoutHelper;
/**
 * @name money
 * @class name：com.king.todo.independent.base
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/26 9:43 PM
 * @change
 * @chang time
 * @class describe
 */
public abstract class IBaseActivity<DataBinding extends ViewDataBinding> extends FragmentActivity {
   private transient LayoutHelper layoutHelper;
   private transient int var =BR.vm;
    private DataBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutId(), null, false);
        binding.setVariable(var,this);
        binding.executePendingBindings();
        setContentView(binding.getRoot());
    }

    private LayoutHelper getLayoutHelper()  {
        if(layoutHelper==null)
            layoutHelper = findModelView(this.getClass());
        if (layoutHelper==null)throw new RuntimeException("add @LayoutHelper at you Activity:"+this.getClass());
        return layoutHelper;
    }
    private  @LayoutRes int getLayoutId()  {
        int[] value = getLayoutHelper().value();
        int length = value.length;
        return value[0];
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
    protected DataBinding getDataBinding(){
        return binding;
    }
}
