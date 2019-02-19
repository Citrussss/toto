package com.union.bangbang.todokotlin.ui.home.moment

import android.app.Application
import android.databinding.ObservableField
import android.view.View
import com.amap.api.location.AMapLocationListener
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.net.Location
import com.union.bangbang.todokotlin.base.data.pojo.Memo
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class HomeMomentModel @Inject constructor(val app: Application, val dataService: DataService,val location: Location) : BaseModel(app) {
    var memo: Memo = Memo()
    val locationOb:ObservableField<String> = ObservableField("添加地点")
    fun onSendClick(view: View) {
        addDisposable(dataService.addMemo(memo).observeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .subscribe { ToastUtil.success(it) })
    }
    fun onLocationClick(view: View){
        location.start(AMapLocationListener {
            locationOb.set(it.address)
        })
    }
}
