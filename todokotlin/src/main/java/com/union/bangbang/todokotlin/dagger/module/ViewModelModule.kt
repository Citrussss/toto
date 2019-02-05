package com.union.bangbang.todokotlin.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.union.bangbang.todokotlin.base.model.ViewModelFactory
import com.union.bangbang.todokotlin.dagger.ViewModelKey
import com.union.bangbang.todokotlin.ui.startup.StartUpModel
import com.union.bangbang.todokotlin.ui.user.UserListModel
import com.union.bangbang.todokotlin.ui.user.login.LoginModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * 页面描述：ViewModelModule
 * 所有的ViewModel统一在这里管理
 * Created by ditclear on 2018/7/4.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartUpModel::class)
    abstract fun injectStartUpModel(viewModel: StartUpModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserListModel::class)
    abstract fun injectUserListModel(viewModel: UserListModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginModel::class)
    abstract fun injectLoginModel(viewModel: LoginModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}