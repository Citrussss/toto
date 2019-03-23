package com.union.bangbang.todokotlin.ui.startup

import android.app.Application
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.util.Log
import android.view.View
import com.union.bangbang.todokotlin.Constants
import com.union.bangbang.todokotlin.TodoApplication
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.pojo.bing.BingWallpageEntity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.okhttp.Reaper
import com.union.bangbang.todokotlin.base.utils.ArouterUtil
import com.union.bangbang.todokotlin.dagger.module.ActivityModule
import com.union.bangbang.todokotlin.ui.user.UserListActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * 页面描述：StartUpModel
 * @param animal 数据源Model(MVVM 中的M),负责提供ViewModeeeeeeeeel中需要处理的数eee据
 * Created by ditclear on 2017/11/17.
 */
class StartUpModel @Inject constructor(val dataService: DataService, app: Application) : BaseModel(app) {
    var textOb: ObservableField<String> = ObservableField()
    var urlOb = ObservableField<String>()
    var timeOb = ObservableField<String>()
    fun onNetClick(view: View) {
        refresh()
    }

    var entity: BingWallpageEntity? = null
    var onNext: Consumer<Boolean>? = null;
    fun refresh() {
        dataService.getWallpaper(format = "js", idx = 0, n = 1).subscribe(Reaper(
                this,
                Consumer {
                    entity = it
                    urlOb.set(if (it.images[0].url.contains("http")) {
                        it.images[0].url
                    } else {
                        Constants.KEY_Bing + it.images[0].url
                    })
                    textOb.set(it.images[0].copyright)
                    Log.w("bing", urlOb.get())
                })
        )
    }

    fun startTiming(onNext: Consumer<Boolean>,time :Long=3) {
        this.onNext = onNext
        addDisposable(Observable.interval(0, 1000, TimeUnit.MILLISECONDS).take(time + 1)
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    timeOb.set(String.format(Locale.CHINESE, "等待跳过%1sS", (time - it)))
                    if (time == it) {
                        timeOb.set("结束")
                        onNext.accept(true)
                    }
                })
    }

    fun onNextClick(view: View) {
        UserListActivity.onStartActivity(getApplication())
    }

    fun goHome() = {
        ArouterUtil.navigation(ActivityModule.home_page)
    }

    fun onUnderstandMoreClick(view: View) {
        entity?.images?.get(0)?.copyrightlink.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it)
            getApplication<TodoApplication>().startActivity(intent)
        }
    }

    fun onSkipClick(view: View) {
        onNext?.accept(true)
    }
}