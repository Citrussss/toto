package com.union.bangbang.todokotlin.base.data.net

import android.Manifest
import android.content.Context
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import com.union.bangbang.todokotlin.base.utils.request
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.app
import com.union.bangbang.zero.AppUtil
import io.reactivex.functions.Consumer
import javax.inject.Inject


/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
class Location @Inject constructor(context: Context) {

    lateinit var locationClient: AMapLocationClient
    lateinit var mLocationOption: AMapLocationClientOption
    lateinit var geocoderSearch: GeocodeSearch

    init {
//声明定位回调监听器
        val mLocationListener = AMapLocationListener(function = {})
//初始化定位
        locationClient = AMapLocationClient(context)
//设置定位回调监听
        locationClient.setLocationListener(mLocationListener)


//初始化AMapLocationClientOption对象
        mLocationOption = AMapLocationClientOption()
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        locationClient.stopLocation();
        locationClient.startLocation();
        geocoderSearch = GeocodeSearch(context)
    }

    fun startLocation(listener: AMapLocationListener) {
        stop()
        locationClient.setLocationListener {
            run {
                if (it.errorCode == 0) {
//                    if (TextUtils.isEmpty(it.address)){
//                        val latlon =LatLonPoint(it.latitude,it.longitude)
//                        val query = RegeocodeQuery(latlon, 10F, GeocodeSearch.AMAP)
//                        geocoderSearch.getFromLocationAsyn(query)
//                    }
                    listener.onLocationChanged(it)
                } else {
                    ToastUtil.error(it.errorInfo)
                }
                locationClient.stopLocation()
            }
        }
        request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(Consumer {
            if (it.granted) locationClient.startLocation()
            else if (it.shouldShowRequestPermissionRationale) ToastUtil.error("请打开定位权限，否者无法获得定位信息")
            else ToastUtil.error("请手动打开定位权限，否者无法获得定位信息")
        }, Consumer {
            ToastUtil.error(it.message.toString())
        })
    }

    private fun stop() {
        locationClient.stopLocation()
        locationClient.setLocationListener(AMapLocationListener(function = {}))
    }

    fun regeocode(location: AMapLocation, listener: GeocodeSearch.OnGeocodeSearchListener) {
        val latlon = LatLonPoint(location.latitude, location.longitude)
        regeocode(latlon, listener)
    }

    fun regeocode(latlon: LatLonPoint, listener: GeocodeSearch.OnGeocodeSearchListener) {
        val query = RegeocodeQuery(latlon, 10F, GeocodeSearch.AMAP)
        geocoderSearch.setOnGeocodeSearchListener(listener)
        geocoderSearch.getFromLocationAsyn(query)
    }

}