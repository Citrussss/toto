package com.union.bangbang.todokotlin.dagger.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.union.bangbang.todokotlin.base.model.ViewModelFactory
import com.union.bangbang.todokotlin.dagger.ViewModelKey
import com.union.bangbang.todokotlin.ui.home.HomeModel
import com.union.bangbang.todokotlin.ui.home.moment.HomeMomentModel
import com.union.bangbang.todokotlin.ui.home.page.HomePageModel
import com.union.bangbang.todokotlin.ui.home.surrounding.HomeSurroundingModel
import com.union.bangbang.todokotlin.ui.startup.StartUpModel
import com.union.bangbang.todokotlin.ui.user.UserListModel
import com.union.bangbang.todokotlin.ui.user.collect.CollectModel
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
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StartUpModel::class)
    abstract fun bindStartUpModel(viewModel: StartUpModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserListModel::class)
    abstract fun bindUserListModel(viewModel: UserListModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginModel::class)
    abstract fun bindLoginModel(viewModel: LoginModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeModel::class)
    abstract fun bindHomeModel(viewModel: HomeModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomePageModel::class)
    abstract fun bindHomePageModel(viewModel: HomePageModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeMomentModel::class)
    abstract fun bindHomeMomentModel(viewModel: HomeMomentModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeSurroundingModel::class)
    abstract fun bindHomeSurroundingModel(viewModel: HomeSurroundingModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CollectModel::class)
    abstract fun bindCollectModel(viewModel: CollectModel): ViewModel
}