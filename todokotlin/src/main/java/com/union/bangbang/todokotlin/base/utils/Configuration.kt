package com.union.bangbang.todokotlin.base.utils

import android.content.Context
import android.text.TextUtils
import com.union.bangbang.todokotlin.BuildConfig
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
object Configuration {
    var token: String = ""
    val properties = Properties()
    var initProperties = false;
    fun setToken(content: Context, token: String) {
        this.token = token
        val sp = content.getSharedPreferences(BuildConfig.VERSION_NAME, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString("token", token)
        edit.apply()
    }

    fun getToken(content: Context): String? {
        if (TextUtils.isEmpty(token)) {
            val sp = content.getSharedPreferences(BuildConfig.VERSION_NAME, Context.MODE_PRIVATE)
            sp.getString("token", null)?.let { token = it }
        }
        return token
    }

    @Synchronized
    fun getValue(key: String): Any? {
        return properties[key]
    }

    @Synchronized
    fun setValue(key: String, value: String) {
        properties.setProperty(key,value)
    }
    @Synchronized
    fun initPro(content: Context) {
        if (!initProperties) {
            initProperties = true
            properties.load(content.getAssets().open("appConfig"))
        }
    }
}