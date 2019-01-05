package com.union.bangbang.todokotlin.ui.startup

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.BaseActivity
import com.union.bangbang.todokotlin.databinding.ActivityStartupBinding
import com.union.bangbang.todokotlin.ui.user.UserListActivity


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
    override fun getLayoutId(): Int = R.layout.activity_startup
}
