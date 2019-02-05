package com.union.bangbang.todokotlin.ui.startup

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.utils.ArouterUtil
import com.union.bangbang.todokotlin.dagger.module.ActivityModule
import com.union.bangbang.todokotlin.ui.user.UserListActivity

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 页面描述：StartUpModel
 * @param animal 数据源Model(MVVM 中的M),负责提供ViewModeeeeeeeeel中需要处理的数eee据
 * Created by ditclear on 2017/11/17.
 */
class StartUpModel @Inject constructor(private val dataService: DataService,private val app:Application) : AndroidViewModel(app) {
    var text: ObservableField<String> = ObservableField()
    var visible: ObservableBoolean = ObservableBoolean()

    fun setTourist() = dataService.tourist().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.trampoline())
            .subscribe({ text.set(it.data.token.token) }, { Log.e("StartUpModel", it.message) })

    fun onNetClick(view: View) {
        setTourist()
    }
    fun onNextClick(view :View){
        UserListActivity.onStartActivity(getApplication())
    }
    fun goHome()={
        ArouterUtil.navigation(ActivityModule.user_login)
    }
}