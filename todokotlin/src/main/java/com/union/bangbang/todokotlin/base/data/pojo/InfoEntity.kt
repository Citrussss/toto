package com.union.bangbang.todokotlin.base.data.pojo

import android.databinding.ViewDataBinding
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.recycle.adapter.ViewSelectHelper

/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
data class InfoEntity<T>(
        val code: Int,
        val data: T
)

data class TouristEntity(
        val token: Token,
        val user: User
)

//data class Token(
//        val id: Int,
//        val token: String,
//        val userEntity: User
//)

//data class User(var id: Int, val name: String) : ViewSelectHelper<ViewDataBinding> {
//    override var index: Int
//        get() = 0 //To change initializer of created properties use File | Settings | File Templates.
//        set(value) {}
//    override var layoutId: IntArray
//        get() = intArrayOf(R.layout.holder_user)
//        set(value) {}
//}


