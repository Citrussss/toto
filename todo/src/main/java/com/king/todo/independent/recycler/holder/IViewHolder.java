package com.king.todo.independent.recycler.holder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.king.todo.independent.base.IBaseEntity;

/**
 * @name money
 * @class name：com.king.todo.independent.recycler.adapter
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/26 10:19 PM
 * @change
 * @chang time
 * @class describe
 */
@SuppressWarnings("unchecked")
public class IViewHolder<E extends IBaseEntity> extends RecyclerView.ViewHolder {
    private E e;
    private ViewDataBinding dataBinding;
    private  ViewGroup container;
    private static int i=0;
    private IViewHolder(ViewGroup container, ViewDataBinding binding) {
        super(binding.getRoot());
        dataBinding=binding;
    }
    public IViewHolder(E entity,ViewGroup container){
        this(container,entity.attach(container,null));
        this.e=entity;
        this.container=container;
    }
    public void refresh(E e){
        this.e.removeBind();
        this.e=e;
        dataBinding=e.attach(container,dataBinding);
//        e.attach(container);
    }
}
