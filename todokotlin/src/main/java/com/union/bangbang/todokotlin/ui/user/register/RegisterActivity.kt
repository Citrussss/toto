package com.union.bangbang.todokotlin.ui.user.register

import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.user_register
import com.union.bangbang.todokotlin.databinding.ActivityRegisterBinding
import com.union.bangbang.todokotlin.ui.user.login.LoginModel
import javax.inject.Inject

/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
@Route(path = user_register)
class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    @Inject
    lateinit var viewModel: LoginModel

    override fun getLayoutId(): Int = R.layout.activity_register
    override fun initViewModel(): BaseModel? = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        setImmersive()
        super.onCreate(savedInstanceState)
    }
}