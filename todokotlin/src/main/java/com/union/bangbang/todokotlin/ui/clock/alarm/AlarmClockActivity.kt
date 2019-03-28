package com.union.bangbang.todokotlin.ui.clock.alarm

import android.os.Bundle
import android.widget.SeekBar
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.getColor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_clock_alarm.*
import java.util.concurrent.TimeUnit

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/28 7:00 PM
 * 沉着冷静面对⛈️
 */
class AlarmClockActivity : BaseActivity<com.union.bangbang.todokotlin.databinding.ActivityClockAlarmBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_clock_alarm
    }

    /**
     * 同时Activity 如果要使用@Inject的话 请去下面这个地方注册
     * @see com.union.bangbang.todokotlin.dagger.module.ActivityModule
     * 如果要使用ViewModel， 请去下面这个地方注册
     * @see com.union.bangbang.todokotlin.dagger.module.ViewModelModule
     */
    override fun initViewModel(): BaseModel? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setImmersive()
        ripple_view.addPop(RippleView.CircleEntity(100F, window.decorView.getColor(R.color.white), 255))

        ripple_view.addPop(RippleView.CircleEntity(200F, window.decorView.getColor(R.color.white), 255))

        ripple_view.addPop(RippleView.CircleEntity(300F, window.decorView.getColor(R.color.white), 255))

        ripple_view.addPop(RippleView.CircleEntity(400F, window.decorView.getColor(R.color.white), 255))

        Observable.interval(0, 1, TimeUnit.SECONDS).take(Long.MAX_VALUE)
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    ripple_view.setTime(System.currentTimeMillis() + 1000 * 60 * it)
                    when (it % 5) {
                        0L -> ripple_view.addPop(-1F, window.decorView.getColor(R.color.red), 255)
                        1L -> ripple_view.addPop(-1F, window.decorView.getColor(R.color.yellow), 255)
                        2L -> ripple_view.addPop(-1F, window.decorView.getColor(R.color.blue), 255)
                        3L -> ripple_view.addPop(-1F, window.decorView.getColor(R.color.green), 255)
                        4L -> ripple_view.addPop(-1F, window.decorView.getColor(R.color.pink), 255)

                    }
                }
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var pice: Double = 7.2
                ripple_view.setTime(((12 / 100F * progress) % 12).toInt(), ((pice * progress) % 60).toInt())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
}