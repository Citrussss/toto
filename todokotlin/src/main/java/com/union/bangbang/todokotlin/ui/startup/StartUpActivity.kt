package com.union.bangbang.todokotlin.ui.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.union.bangbang.todokotlin.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_startup.*
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.toObservable
import rx.schedulers.Schedulers

class StartUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

    }
}
