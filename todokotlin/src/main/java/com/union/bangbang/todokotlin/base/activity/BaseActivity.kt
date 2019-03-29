package com.union.bangbang.todokotlin.base.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.union.bangbang.todokotlin.BR
import com.union.bangbang.todokotlin.Constants
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.TransitionTypeValue
import com.union.bangbang.todokotlin.base.utils.TransitionTypeValue.explode
import com.union.bangbang.todokotlin.base.utils.TransitionTypeValue.fade
import com.union.bangbang.todokotlin.base.utils.TransitionTypeValue.slide
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable


/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/16 3:45 PM
 * 只有编译器可能不骗你。
 */
abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: Binding
    private val rxList: ArrayList<Disposable> = ArrayList()
    protected var slidingDirection = Gravity.END
    protected var transitionType = TransitionTypeValue.slide
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.extras?.getString(Constants.Transition_Type, transitionType)?.let { transitionType = it }
            window.enterTransition = when (transitionType) {
                explode -> Explode()
                fade -> Fade()
                slide -> Slide(slidingDirection)
                else -> Fade()
            }
        }
        super.onCreate(savedInstanceState)
        initViewDataBinding(intent.extras)
    }

    companion object Route {
        fun onStartActivity(context: Context, clazz: Class<*>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }

    fun initViewDataBinding(bundle: Bundle?) {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        val viewModel = initViewModel()
        viewModel?.let {
            it.shouldFinish.observe(this, Observer { t -> if (t == true) finish() })
            bundle?.let { viewModel.attachData(it) }
        }
        binding.setVariable(BR.vm, viewModel)
        initToolbar()
    }

    abstract fun getLayoutId(): Int
    /**
     * 同时Activity 如果要提供当前的话Activity作为Dagger的注入素材时 请去下面这个地方注册
     * @see com.union.bangbang.todokotlin.dagger.module.ActivityModule
     * 如果要使用ViewModel， 请去下面这个地方注册
     * @see com.union.bangbang.todokotlin.dagger.module.ViewModelModule
     */
    abstract fun initViewModel(): BaseModel?

    fun addDisposable(disposable: Disposable) {
        rxList.add(disposable)
    }

    fun stopAllDisposable() {
        rxList.forEach({ it.dispose() })
    }

    override fun onDestroy() {
        stopAllDisposable()
        super.onDestroy()
    }

    fun initToolbar() {
        val toolbar = findViewById<View>(R.id.tool_bar)
        toolbar?.let {
            it.findViewById<TextView>(R.id.title)?.let { it.setText(title) }
            it.findViewById<View>(R.id.back_layout).setOnClickListener { onBackPressed() }
        }
    }

    //    open fun Toolbar.setTitleCenter() {
//        childCount
//        for (index in 0..childCount) {
//            val view = getChildAt(index)
//            if (view is TextView) {
//                val textView = view;
//                if (!TextUtils.isEmpty(textView.getText())) {
//                    textView.setGravity(Gravity.CENTER);
//                    val params = FrameLayout.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
//                    params.gravity = Gravity.CENTER;
//                    textView.setLayoutParams(params);
//                }
//            }
//        }
//    }
    /**
     * 开启沉浸式
     */
    fun setImmersive() {
        //显示的优先级可以调整，显示的顺序可以查看WindowManagerPolicy中getWindowLayerFromTypeLw函数中的返回值
        val lp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY)
        } else {
            WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR)
        };

        val flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.decorView.systemUiVisibility = flag
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.window.navigationBarColor = Color.TRANSPARENT;
            this.window.statusBarColor = Color.TRANSPARENT
        };
    }
}