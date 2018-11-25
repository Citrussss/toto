package com.king.todo.independent.recycler.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.king.todo.independent.base.IBaseEntity;
import com.king.todo.independent.recycler.holder.IViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @name money
 * @class name：com.king.todo.independent.recycler.adapter
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/26 10:16 PM
 * @change
 * @chang time
 * @class describe
 */
public class IAdapter<E extends IBaseEntity> extends RecyclerView.Adapter<IViewHolder<E>> {
    private final List<E> eList=new ArrayList<>();
    private final SparseArray<E> viewTypeList=new SparseArray<>();
    @NonNull
    @Override
    public IViewHolder<E> onCreateViewHolder(@NonNull ViewGroup viewGroup,int viewType) {
        return new IViewHolder<>(viewTypeList.get(viewType),viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull IViewHolder<E> iViewHolder, int i) {
        E e = eList.get(i);
        iViewHolder.refresh(e);
    }

    @Override
    public int getItemViewType(int position) {
        E e = eList.get(position);
        int id =e.getLayoutId();
        viewTypeList.put(id,e);
        return id;
//        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return eList.size();
    }
    public void seteList(List<E> list){
        eList.addAll(list);
        notifyDataSetChanged();
    }
}
