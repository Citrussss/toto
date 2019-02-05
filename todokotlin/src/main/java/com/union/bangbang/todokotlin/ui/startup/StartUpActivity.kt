package com.union.bangbang.todokotlin.ui.startup

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.BaseActivity
import com.union.bangbang.todokotlin.base.utils.ArouterUtil
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_login
import com.union.bangbang.todokotlin.databinding.ActivityStartupBinding
import com.union.bangbang.todokotlin.ui.user.UserListActivity
import com.union.bangbang.todokotlin.ui.user.login.LoginActivity


import kotlinx.android.synthetic.main.activity_startup.*

import javax.inject.Inject


class StartUpActivity : BaseActivity<ActivityStartupBinding>() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: StartUpModel
    override fun initViewModel(): AndroidViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(StartUpModel::class.java)
        viewModel.setTourist()
        next.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
        return viewModel;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ArouterUtil.navigation(user_login)
        finish()
    }
    override fun getLayoutId(): Int = R.layout.activity_startup
}
