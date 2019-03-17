package com.union.bangbang.todokotlin.ui.user.collect

import android.app.Application
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.model.RecycleModel
import com.union.bangbang.todokotlin.base.okhttp.Reaper
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/3 3:38 PM
 * 只有编译器可能不骗你。
 */
class CollectModel @Inject constructor(val app: Application, val dataService: DataService) : RecycleModel(app) {

    fun refreshList() {
        dataService.findAllCollect().subscribe(
                Reaper(this, Consumer {}, Consumer { ToastUtil::error })
                //            adapter.addData(it.data)
        )
    }
}