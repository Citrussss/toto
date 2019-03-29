package com.union.bangbang.todokotlin.ui.clock.alarm

import android.app.Application
import android.net.Uri
import android.support.v4.util.Consumer
import android.view.View
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.FileDataSourceFactory
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.FileUtil
import com.union.bangbang.zero.util.photo.FileViewManager
import javax.inject.Inject


/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/29 8:39 PM
 * 沉着冷静面对⛈️
 */
class AlarmClockModel @Inject constructor(val app: Application, val player: ExoPlayer) : BaseModel(app), Player.EventListener {
    var musicUrl: String? = "http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/audioes/c2/c14/162b3845f343e.mp3?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1523430231%3B3101353431%26q-key-time%3D1523430231%3B3101353431%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D6b582f947ccb41e6cffa4495773c0c162995306d"
    var userAent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36"
    fun onSettingMusicClick(view: View) {
        FileViewManager.selectFile(Consumer {
            musicUrl = it.absolutePath
            this.musicUrl?.let { startMusic(it) }
        }, FileViewManager.AUDIO)
    }

    fun startMusic(musicUrl: String) {
        val uri = Uri.parse(musicUrl)
        val source: ExtractorMediaSource = if (FileUtil.isWebUrl(musicUrl)) {
            val netSoFactory = DefaultHttpDataSourceFactory(userAent)
            ExtractorMediaSource.Factory(netSoFactory).createMediaSource(uri)
        } else {
            val dataSourceFactory = FileDataSourceFactory()
            ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        }
        player.addListener(this)
        player.prepare(source, false, true)
    }

    fun stopMusic() {
        player.release()
    }

    override fun onCleared() {
        super.onCleared()
        stopMusic()
        player.removeListener(this)
    }
}