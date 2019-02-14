package com.union.bangbang.todokotlin.base.fragment

import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.union.bangbang.todokotlin.BR
import dagger.android.support.AndroidSupportInjection


/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
abstract class BaseFragment<out Binding : ViewDataBinding> : Fragment() {
    lateinit var binding: ViewDataBinding
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<Binding>(inflater, getLayoutId(), container, false)
        binding.setVariable(BR.vm, initViewModel())
        initToolbar(binding)
        return binding.root
    }

    abstract fun initViewModel(): AndroidViewModel?
    abstract fun getLayoutId(): Int

    inline fun initToolbar(binding: ViewDataBinding) {
        val toolbar = binding.root.findViewById<Toolbar>(com.union.bangbang.todokotlin.R.id.tool_bar)
        toolbar?.let { it.setTitleCenter() }
    }

    open fun Toolbar.setTitleCenter() {
        childCount
        for (index in 0..childCount) {
            val view = getChildAt(index)
            if (view is TextView) {
                val textView = view;
                if (!TextUtils.isEmpty(textView.getText())) {
                    textView.setGravity(Gravity.CENTER);
                    val params = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER;
                    textView.setLayoutParams(params);
                }
            }
        }
    }
}