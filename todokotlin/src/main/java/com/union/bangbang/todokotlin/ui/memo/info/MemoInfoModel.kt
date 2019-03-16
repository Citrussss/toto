package com.union.bangbang.todokotlin.ui.memo.info

import android.app.Application
import android.databinding.ObservableField
import android.os.Bundle
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMapUtils
import com.amap.api.maps2d.model.LatLng
import com.union.bangbang.todokotlin.Constants
import com.union.bangbang.todokotlin.base.data.net.Location
import com.union.bangbang.todokotlin.base.data.pojo.Memo
import com.union.bangbang.todokotlin.base.model.BaseModel
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/16 3:03 PM
 * 只有编译器可能不骗你。
 */
class MemoInfoModel @Inject constructor(app: Application, val location: Location) : BaseModel(app) {
    var memo: Memo? = null
    var distance: ObservableField<String> = ObservableField("")
    override fun attachData(bundle: Bundle) {
        super.attachData(bundle)
        memo = (bundle.getParcelable(Constants.Bundle.Memo))
        addDisposable(Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                .subscribe {
                    location.startLocation(AMapLocationListener {
                        if (memo?.latitude != null && memo?.longitude != null) {
                            val latlngA = LatLng(memo!!.latitude!!, memo!!.longitude!!)
                            distance.set(String.format(Locale.CHINESE, "此信息距离您当前的距离为：%1s", AMapUtils.calculateLineDistance(latlngA, LatLng(it.latitude, it.longitude))))
                        }
                    })
                })
    }
}