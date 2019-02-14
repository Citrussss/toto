package com.union.bangbang.todokotlin.ui.home

import android.app.Application
import android.databinding.ViewDataBinding
import android.text.TextUtils
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.fragment.BaseFragment
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.ArouterUtil
import com.union.bangbang.todokotlin.base.utils.UserUtil.getToken
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_login
import com.union.bangbang.todokotlin.ui.home.mine.HomeMineFragment
import com.union.bangbang.todokotlin.ui.home.moment.HomeMomentFragment
import com.union.bangbang.todokotlin.ui.home.page.HomePageFragment
import javax.inject.Inject

/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
class HomeModel @Inject constructor(private val dataService: DataService, private val app: Application) : BaseModel(app) {
    init {
        val token = getToken(app)
        if (TextUtils.isEmpty(token)) {
            ArouterUtil.navigation(user_login)
            finish()
        }
    }

    fun getFragment(position: Int): BaseFragment<ViewDataBinding> {
        return when (position) {

            0 -> HomePageFragment()
            1 -> HomeMomentFragment()
            2 -> HomeMineFragment()
            else -> throw Exception("out of length!")
        }
    }
}