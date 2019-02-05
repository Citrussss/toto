package com.union.bangbang.todokotlin.ui.user

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.BaseActivity
import com.union.bangbang.todokotlin.databinding.ActivityUserListBinding
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/22 6:14 PM
 * 只有编译器可能不骗你。
 */
class UserListActivity : BaseActivity<ActivityUserListBinding>() {
    override fun initViewModel(): AndroidViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(UserListModel::class.java)
        recycle_view.adapter = viewModel.adapter
        recycle_view.layoutManager = viewModel.linearLayoutManager
        viewModel.getUserList()
        return viewModel
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: UserListModel
    private lateinit var s: UserListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    companion object Route {
        fun onStartActivity(context: Context) {
            onStartActivity(context, UserListActivity::class.java)
        }
    }
    override fun getLayoutId(): Int =R.layout.activity_user_list
}