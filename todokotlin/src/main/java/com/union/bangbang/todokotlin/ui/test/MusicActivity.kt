package com.union.bangbang.todokotlin.ui.test

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.music
import com.union.bangbang.todokotlin.databinding.ActivityMusicBinding

/**
 * Rabies
 * @author USER
 * Date:   2019-03-05
 * Time:   16:10
 */
@Route(path = music)
class MusicActivity : BaseActivity<ActivityMusicBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_music

    override fun initViewModel(): BaseModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}