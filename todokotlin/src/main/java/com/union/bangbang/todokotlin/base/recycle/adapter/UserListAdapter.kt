package com.union.bangbang.todokotlin.base.recycle.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.data.pojo.User

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/22 5:07 PM
 * 只有编译器可能不骗你。
 */
class UserListAdapter(data: MutableList<User>?) : BaseQuickAdapter<User, BaseViewHolder>(data) {
    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder?, item: User?) {
        if (helper != null && item != null) {
            helper.setText(R.id.nickname, item.name)
                    .setText(R.id.token, item.id.toString())
        }
    }

    init {
        multiTypeDelegate = object : MultiTypeDelegate<User>() {
            override fun getItemType(entity: User): Int {
                //根据你的实体类来判断布局类型
                return 127
            }
        }
        multiTypeDelegate.registerItemType(127, R.layout.holder_user)
    }

}