package com.union.bangbang.todokotlin.ui.home.moment

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.Application
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.os.Build
import android.text.TextUtils
import android.view.View
import com.amap.api.location.AMapLocationListener
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeResult
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.net.Location
import com.union.bangbang.todokotlin.base.data.pojo.Memo
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import com.union.bangbang.todokotlin.ui.user.login.LoginActivity
import com.union.bangbang.zero.util.DateUtil
import com.union.bangbang.zero.util.photo.TimePickerHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class HomeMomentModel @Inject constructor(val app: Application, val dataService: DataService, val location: Location) : BaseModel(app), GeocodeSearch.OnGeocodeSearchListener {

    lateinit var dataPickerDialog: DatePickerDialog
    var memo: Memo = Memo()
    val locationOb: ObservableField<String> = ObservableField(app.getString(R.string.add_place))
    val timeOb: ObservableField<String> = ObservableField(app.getString(R.string.select_time))
    fun onSendClick(view: View) {
        addDisposable(dataService.addMemo(memo).observeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .subscribe { ToastUtil.success(it) })
    }

    fun onLocationClick(view: View) {
        location.startLocation(AMapLocationListener {
            if (TextUtils.isEmpty(it.address)) {
                location.regeocode(it, this)
            } else {
                locationOb.set(it.address)
            }
        })
    }

    override fun onRegeocodeSearched(p0: RegeocodeResult?, p1: Int) {
        p0?.let { it.regeocodeAddress?.let { locationOb.set(it.formatAddress) } }
    }

    override fun onGeocodeSearched(p0: GeocodeResult?, p1: Int) {
        ToastUtil.success("这是地理解码")
    }

    fun onSelectTimeClick(view: View) {
        TimePickerHelper.onSelectTimeClick(view,
                { date, v ->
                    val service: AlarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        service.set(RTC_WAKEUP, date.time, "yyyy年MM月dd日", {
                            app.startActivity(getIntent())
                        }, null)
                    } else {
                        service.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),  getPendingIntent())
                    }
                    timeOb.set(DateUtil.format(date, "yyyy年MM月dd日 HH:mm:ss"))
                },
                "",
                false).show()
    }

    inline fun getPendingIntent(): PendingIntent {
        val intent = getIntent()
        return PendingIntent.getActivity(app, 1, intent, 0)
    }

   inline fun getIntent(): Intent {
        val intent = Intent(app, LoginActivity::class.java);
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        return intent
    }

  /*  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun doService() {
        val jobScheduler = app.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler?
        val builder = JobInfo.Builder(1, ComponentName(AppUtil.peekActivity(), ClockService::class.java))  //指定哪个JobService执行操作
        builder.setMinimumLatency(TimeUnit.MILLISECONDS.toMillis(10)) //执行的最小延迟时间
        builder.setOverrideDeadline(TimeUnit.MILLISECONDS.toMillis(15))  //执行的最长延时时间
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NOT_ROAMING)  //非漫游网络状态
        builder.setBackoffCriteria(TimeUnit.MINUTES.toMillis(10), JobInfo.BACKOFF_POLICY_LINEAR)  //线性重试方案
        builder.setRequiresCharging(false) // 未充电状态
        jobScheduler!!.schedule(builder.build())
    }*/
}
