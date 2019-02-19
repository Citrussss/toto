package com.union.bangbang.todokotlin.base.data.net

import android.content.Context
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
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
    }
    fun start(listener: AMapLocationListener){
        stop()
        locationClient.setLocationListener(listener)
        locationClient.startLocation()
    }
    fun stop(){
        locationClient.stopLocation()
        locationClient.setLocationListener(AMapLocationListener(function = {}))
    }
}