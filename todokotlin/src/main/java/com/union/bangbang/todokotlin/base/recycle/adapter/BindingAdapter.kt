package com.union.bangbang.todokotlin.base.recycle.adapter

import android.databinding.ViewDataBinding
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.union.bangbang.todokotlin.BR
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.data.pojo.User
import com.union.bangbang.zero.AppUtil

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/22 5:07 PM
 * 只有编译器可能不骗你。
 */
class BindingAdapter(data: MutableList<out ViewSelectHelper<ViewDataBinding>>?) : BaseQuickAdapter<ViewSelectHelper<ViewDataBinding>, BaseHolder>(data) {
    val modelList: SparseArray<ViewSelectHelper<ViewDataBinding>> = SparseArray()
    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseHolder?, item: ViewSelectHelper<ViewDataBinding>?) {
        helper?.binding?.setVariable(BR.vm, item)
//        if (helper != null && item != null) {
//            helper.setText(R.id.nickname, item.name)
//                    .setText(R.id.token, item.id.toString())
//        }
    }

    init {
        multiTypeDelegate = object : MultiTypeDelegate<ViewSelectHelper<ViewDataBinding>>() {
            override fun getItemType(entity: ViewSelectHelper<ViewDataBinding>): Int {
                //根据你的实体类来判断布局类型
                modelList.put(entity.getLayoutId(), entity)
                return entity.getLayoutId()
            }
        }
//        multiTypeDelegate.registerItemType(127, R.layout.holder_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val entity = modelList.get(viewType)
        return if (entity != null)
            BaseHolder(entity.getDatabinding(AppUtil.peekActivity(), parent, false))
        else
            super.onCreateViewHolder(parent, viewType)
    }

}