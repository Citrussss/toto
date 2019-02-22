package com.union.bangbang.todokotlin.ui.home.surrounding

import android.app.Application
import android.view.View
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.model.RecycleModel
import javax.inject.Inject
import android.content.Intent
import com.union.bangbang.todokotlin.service.ClockService


/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
class HomeSurroundingModel @Inject constructor(val app: Application, val dataService: DataService) : RecycleModel(app) {

    fun onStartServiceClick(view: View) {
        val it = Intent(app, ClockService::class.java)
        app.startService(it)
    }
    fun onStopServiceClick(view: View){
        val it = Intent(app, ClockService::class.java)
        app.startService(it)
    }
}