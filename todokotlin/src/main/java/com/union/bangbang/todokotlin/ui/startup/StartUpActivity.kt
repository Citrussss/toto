package com.union.bangbang.todokotlin.ui.startup

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


class StartUpActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: StartUpModel
    lateinit var binding: ActivityStartupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(StartUpModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_startup)
        binding.vm = viewModel
        viewModel.setTourist()
        next.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}
