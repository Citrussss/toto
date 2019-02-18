package com.union.bangbang.todokotlin.ui.home.moment

import android.app.Application
import android.view.View
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.pojo.Memo
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class HomeMomentModel @Inject constructor(val app: Application, val dataService: DataService) : BaseModel(app) {
    var memo: Memo = Memo()

    fun onSendClick(view: View) {
        addDisposable(dataService.addMemo(memo).observeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .subscribe { ToastUtil.success(it) })
    }
}
