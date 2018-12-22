package com.union.bangbang.todokotlin.ui.startup

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.model.BaseModel
import io.reactivex.Scheduler

import io.reactivex.Single
import io.reactivex.internal.operators.observable.ObservableFilter
import io.reactivex.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * 页面描述：StartUpModel
 * @param animal 数据源Model(MVVM 中的M),负责提供ViewModeeeeeeeeel中需要处理的数eee据
 * Created by ditclear on 2017/11/17.
 */
class StartUpModel @Inject constructor(private val dataService: DataService) : BaseModel() {
    var text: ObservableField<String> = ObservableField()
    var visible: ObservableBoolean = ObservableBoolean()

    fun setTourist() = dataService.tourist().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.trampoline())
            .subscribe ({ text.set(it.data.token.token)}, {Log.e("StartUpModel",it.message)})

    public fun onNetClick(view:View){
        setTourist()
    }
}