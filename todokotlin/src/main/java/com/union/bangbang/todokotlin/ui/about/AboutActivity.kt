package com.union.bangbang.todokotlin.ui.about

import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.about
import com.union.bangbang.todokotlin.ui.user.login.LoginModel
import javax.inject.Inject

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/2/27 9:22 PM
 * 只有编译器可能不骗你。
 */
@Route(path = about)
class AboutActivity : BaseActivity<com.union.bangbang.todokotlin.databinding.ActivityAboutBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun initViewModel(): BaseModel? {
        return viewModel;
    }
    @Inject lateinit var viewModel: LoginModel
}