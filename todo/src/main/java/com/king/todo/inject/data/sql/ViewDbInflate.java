package com.king.todo.inject.data.sql;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binding.model.App;
import com.binding.model.adapter.IEventAdapter;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Inflate;
import com.binding.model.model.inter.Parse;
import com.binding.model.model.inter.Recycler;
import com.binding.model.util.BaseUtil;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

public class ViewDbInflate<Binding extends ViewDataBinding> extends BaseModel implements Recycler<Binding> {
    private transient int holder_position=-1;
    private transient Binding dataBinding;
    private transient IEventAdapter iEventAdapter;
    private transient boolean live = false;
    private transient ModelView modelView;
    private transient int modelIndex = 0;

    public ViewDbInflate() {
        this.modelView = BaseUtil.findModelView(getClass());
    }

    @Override
    public Binding attachView(Context context, ViewGroup co, boolean attachToParent, Binding binding) {
        if (co != null) {
            Object o = co.getTag(com.binding.model.R.id.holder_position);
            if (o instanceof Integer) this.holder_position = (int) o;
        }
        registerEvent();
        return dataBinding =  bind(getLayoutId(),context, co, attachToParent, binding);
    }

    public final <B extends ViewDataBinding>B bind(int layoutId, Context context, ViewGroup co, boolean attachToParent, B binding) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context),layoutId, co, attachToParent);
            binding.setVariable(getVariableName(), this);
        } else {
            binding.setVariable(getVariableName(), this);
            binding.executePendingBindings();
        }
        return binding;
    }

    @Override
    public final void registerEvent() {
        live = true;
        for(int eventId :getModelView().event()){
            eventSet.put(eventId, this);
        }
    }

    @Override
    public final void unRegisterEvent() {
        live = false;
        for(int eventId :getModelView().event()) {
            eventSet.remove(eventId);
        }
    }

    @Override
    public boolean isLive() {
        return live;
    }

    @Override
    public int onEvent(View view, Event event, Object... args) {
        return 0;
    }


    public final int event(int eventId, View view,Object... args) {
        return Event.event(eventId,this,view,args);
    }


    public final Binding getDataBinding() {
        return dataBinding;
    }

    @Override
    public final void setIEventAdapter(IEventAdapter iEventAdapter) {
        this.iEventAdapter = iEventAdapter;
    }

    @Override
    public final IEventAdapter getIEventAdapter() {
        return iEventAdapter;
    }

    @CallSuper
    @Override
    public void removeBinding() {
        this.dataBinding = null;
        unRegisterEvent();
    }


    public void clear(List<? extends Inflate> list) {
        if (list != null)
            for (Inflate inflate : list) {
                inflate.setIEventAdapter(null);
            }
    }



    @Override
    public boolean areContentsTheSame(Parse parseRecycler) {
        return this.equals(parseRecycler);
    }

    @Override
    public int checkWay() {
        return 3;
    }

    @Override
    public void check(boolean checked) {
    }

    @Override
    public Object key() {
        return this;
    }

    @Override
    public int getSpanSize() {
        return 1;
    }

    @Override
    public int getCheckType() {
        return 0;
    }

    public int getHolder_position() {
        return holder_position;
    }


    @Override
    public final int getLayoutId(){
        return getLayoutId(getModelIndex());
    }

    public final @LayoutRes
    int getLayoutId(int viewType){
        int[] layout = getModelView().value();
        int length = layout.length;
        return layout[getModelIndex() < length ? getModelIndex() : 0];
    }

    @Override
    public final ModelView getModelView() {
        if(modelView == null){
            modelView = BaseUtil.findModelView(getClass());
            if(modelView == null) throw new RuntimeException("should to add_friends @ModelView to the class:" + getClass());
        }
        return modelView;
    }

    @Override
    public final void setModelIndex(int modelIndex) {
        this.modelIndex =modelIndex;
    }

    @Override
    public int getModelIndex() {
        return modelIndex;
    }

    public final int getVariableName() {
        int[] bindName = getModelView().name();
        int length = bindName.length;
        return getModelIndex() < length ? bindName[getModelIndex()] : App.vm;
    }

    public ViewDbInflate saveEntity() {
        if (!exists()) insert();
        return this;
    }
}
