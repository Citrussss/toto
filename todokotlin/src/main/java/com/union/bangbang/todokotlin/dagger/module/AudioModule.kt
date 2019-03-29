package com.union.bangbang.todokotlin.dagger.module

import android.content.Context
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/29 9:16 PM
 * 沉着冷静面对⛈️
 */
@Module
class AudioModule {
    @Provides
    @Singleton
    fun getPlayer(context: Context): ExoPlayer {
        val instance = ExoPlayerFactory.newSimpleInstance(
                context,
                DefaultRenderersFactory(context),
                DefaultTrackSelector(),
                DefaultLoadControl()
        )
        instance.playWhenReady=true
//        instance.seekTo(0,0)
        return instance
    }
}