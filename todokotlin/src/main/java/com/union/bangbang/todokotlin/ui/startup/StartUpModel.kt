package com.union.bangbang.todokotlin.ui.startup

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.View
import com.union.bangbang.todokotlin.base.BaseActivity
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.pojo.User
import com.union.bangbang.todokotlin.ui.user.UserListActivity
import com.union.bangbang.zero.AppUtil

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
}