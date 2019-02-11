package com.union.bangbang.todokotlin.ui.home.moment

import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.fragment.BaseFragment
import com.union.bangbang.todokotlin.databinding.FragmentHomeMomentBinding
import com.union.bangbang.todokotlin.ui.user.UserListModel
import com.union.bangbang.todokotlin.ui.user.UserListModel_Factory
import kotlinx.android.synthetic.main.fragment_home_moment.*
import javax.inject.Inject

/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
class HomeMomentFragment : BaseFragment<FragmentHomeMomentBinding>() {
    @Inject
    lateinit var viewModel: UserListModel

    override fun initViewModel(): AndroidViewModel? = viewModel

    override fun getLayoutId(): Int = R.layout.fragment_home_moment
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycle_view.adapter = viewModel.adapter
        recycle_view.layoutManager = viewModel.linearLayoutManager
        viewModel.getUserList()
    }
}