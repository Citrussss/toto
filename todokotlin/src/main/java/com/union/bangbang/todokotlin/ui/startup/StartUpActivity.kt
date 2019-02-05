package com.union.bangbang.todokotlin.ui.startup

import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.utils.ArouterUtil
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.home_page
import com.union.bangbang.todokotlin.databinding.ActivityStartupBinding


class StartUpActivity : BaseActivity<ActivityStartupBinding>() {
    override fun initViewModel(): AndroidViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ArouterUtil.navigation(home_page)
        finish()
    }
    override fun getLayoutId(): Int = R.layout.activity_startup
}
