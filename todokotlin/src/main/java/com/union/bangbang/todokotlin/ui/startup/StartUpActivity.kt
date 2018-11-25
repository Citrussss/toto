package com.union.bangbang.todokotlin.ui.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.union.bangbang.todokotlin.R
import kotlinx.android.synthetic.main.activity_startup.*
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.toObservable
import rx.schedulers.Schedulers

class StartUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)
        var list = listOf("3", "2", "1")
        list.toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t: String? ->
                run {
                    start_text.text = t
                    Log.v("text", t)
                }
            }
    }
}
