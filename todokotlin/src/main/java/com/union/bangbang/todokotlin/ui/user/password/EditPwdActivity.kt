package com.union.bangbang.todokotlin.ui.user.password

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_pwd
import com.union.bangbang.todokotlin.ui.user.login.LoginModel
import javax.inject.Inject

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/2/27 8:00 PM
 * 只有编译器可能不骗你。
 */

@Route(path = user_pwd)
class EditPwdActivity : BaseActivity<com.union.bangbang.todokotlin.databinding.ActivityPwdBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_pwd
    }

    override fun initViewModel(): BaseModel? {
        return viewModel
    }

    @Inject
    lateinit var viewModel: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}