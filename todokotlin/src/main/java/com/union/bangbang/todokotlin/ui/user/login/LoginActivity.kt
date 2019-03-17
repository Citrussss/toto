package com.union.bangbang.todokotlin.ui.user.login

import android.os.Bundle
import android.view.Gravity
import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_login
import com.union.bangbang.todokotlin.databinding.ActivityLoginBinding
import javax.inject.Inject

/**
 * Rabies
 * @author USER
 * Date:   2019-01-21
 * Time:   13:49
 */
@Route(path = user_login)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    @Inject
    lateinit var viewModel: LoginModel

    override fun getLayoutId(): Int = R.layout.activity_login
    override fun initViewModel(): LoginModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        slidingDirection = Gravity.BOTTOM
        super.onCreate(savedInstanceState)
    }
}