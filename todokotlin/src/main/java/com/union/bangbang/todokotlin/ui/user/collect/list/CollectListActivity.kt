package com.union.bangbang.todokotlin.ui.user.collect.list

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.dagger.module.ActivityModule
import com.union.bangbang.todokotlin.ui.user.collect.CollectModel
import javax.inject.Inject

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/3 3:29 PM
 * 只有编译器可能不骗你。
 */
@Route(path = ActivityModule.collect_list)
class CollectListActivity : BaseActivity<com.union.bangbang.todokotlin.databinding.ActivityCollectListBinding>(){
    override fun getLayoutId(): Int {
        return R.layout.activity_collect_list
    }

    override fun initViewModel(): BaseModel? {
        return viewModel
    }

    @Inject
    lateinit var viewModel: CollectModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recycleView.layoutManager = viewModel.linearLayoutManager
        binding.recycleView.adapter = viewModel.adapter
        viewModel.refreshList()
    }
}