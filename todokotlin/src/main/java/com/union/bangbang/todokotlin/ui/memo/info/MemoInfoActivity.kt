package com.union.bangbang.todokotlin.ui.memo.info

import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.memo_info
import javax.inject.Inject

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/16 2:31 PM
 * 只有编译器可能不骗你。
 */
@Route(path = memo_info)
class MemoInfoActivity : BaseActivity<com.union.bangbang.todokotlin.databinding.ActivityMemoInfoBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_memo_info
    }

    /**
     * 同时Activity 如果要使用@Inject的话 请去下面这个地方注册
     * @see com.union.bangbang.todokotlin.dagger.module.ActivityModule
     * 如果要使用ViewModel， 请去下面这个地方注册
     * @see com.union.bangbang.todokotlin.dagger.module.ViewModelModule
     */
    override fun initViewModel(): BaseModel? {
        return viewModel
    }

    @Inject
    lateinit var viewModel: MemoInfoModel
}