package com.union.bangbang.todokotlin.base.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import io.reactivex.disposables.Disposable


/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
open class BaseModel constructor(app: Application) : AndroidViewModel(app) {
    private val rxList: ArrayList<Disposable> = ArrayList()
    val shouldFinish: MutableLiveData<Boolean> = MutableLiveData()
    val shouldRefreshModel:MutableLiveData<Int> = MutableLiveData()
    var refreshCount = 0
    open fun attachData(bundle: Bundle) {

    }

    fun addDisposable(disposable: Disposable) {
        rxList.add(disposable)
    }

    fun stopAllDisposable() {
        rxList.forEach { it.dispose() }
    }

    override fun onCleared() {
        stopAllDisposable()
        super.onCleared()
    }

    open fun finish() = shouldFinish.postValue(true)
    open fun refreshModel() = shouldRefreshModel.postValue(refreshCount++)
}