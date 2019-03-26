package com.union.bangbang.todokotlin.ui.home.page

import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.fragment.BaseFragment
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
class HomePageFragment : BaseFragment<com.union.bangbang.todokotlin.databinding.FragmentHomePageBinding>(), CalendarView.OnCalendarSelectListener {
    /**
     * 日期选择事件
     *
     * @param calendar calendar
     * @param isClick  isClick
     */
    override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
        calendar?.timeInMillis?.let {
            viewModel.refreshData(it, it + 24 * 60 * 60 * 1000)
        }
    }

    /**
     * 超出范围越界
     *
     * @param calendar calendar
     */
    override fun onCalendarOutOfRange(calendar: Calendar?) {
        ToastUtil.error("超出范围越界")
    }

    @Inject
    lateinit var viewModel: HomePageModel

    override fun initViewModel(): AndroidViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_page
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        EventBus.getDefault().register(this)

        recyclerView.adapter = viewModel.adapter
        recyclerView.layoutManager = viewModel.linearLayoutManager
        calendarView.setOnCalendarSelectListener(this)
//        viewModel.refreshData()
//        val rxPermissions=RxPermissions(this)
//        rxPermissions.ensureEach
    }
    /* @Subscribe(threadMode = ThreadMode.MAIN)
       fun Event(messageEvent: TipEntity) {
           tool_bar.title=(messageEvent.position.toString())
       }*/

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}