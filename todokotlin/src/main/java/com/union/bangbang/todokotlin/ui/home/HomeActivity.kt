package com.union.bangbang.todokotlin.ui.home

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.activity.BaseActivity
import com.union.bangbang.todokotlin.base.fragment.BaseFragment
import com.union.bangbang.todokotlin.base.okhttp.ApiException
import com.union.bangbang.todokotlin.dagger.module.ActivityModule.Companion.home_page
import com.union.bangbang.todokotlin.databinding.ActivityHomeBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
@Route(path = home_page)
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    @Inject
    lateinit var viewModel: HomeModel
    private val fragments: ArrayList<BaseFragment<ViewDataBinding>> = ArrayList()
    private var currentTab: Int = -1
    override fun getLayoutId() = R.layout.activity_home
    override fun initViewModel(): HomeModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDisposable(
                Observable.range(0, 3)
                        .map { viewModel.getFragment(it) }.toList().toObservable()
                        .doOnNext { fragments.addAll(it) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { checkFragment(0) }
        )
        tab_layout.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                Log.i("HomeActivity", "onTabReselected")
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                Log.i("HomeActivity", "onTabUnselected")
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                (p0?.let { checkFragment(it.position) })
            }
        })
        throw ApiException(10, "爆炸了")
    }

    private fun checkFragment(position: Int?) {
        val ft = supportFragmentManager.beginTransaction()
        if (currentTab >= 0) {
            val beforeFragment = fragments.get(currentTab)
            beforeFragment.onPause()
            ft.remove(beforeFragment)
        }
        val fragment = fragments.get(position!!)
        for (i in 0 until fragment_content.childCount - fragments.size)
            fragment_content.removeView(fragment_content.getChildAt(i))
        if (fragment.isAdded)
            fragment.onResume()
        else
            ft.add(R.id.fragment_content, fragment)
        Log.e("HomeActivity", "checkFragment")

        ft.show(fragment)
        ft.commitAllowingStateLoss()
        currentTab = position
    }
}