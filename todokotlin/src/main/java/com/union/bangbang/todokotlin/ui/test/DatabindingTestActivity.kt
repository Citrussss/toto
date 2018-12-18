package com.union.bangbang.todokotlin.ui.test


import android.os.Bundle
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.BaseActivity

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/16 4:22 PM
 * 只有编译器可能不骗你。
 */
class DatabindingTestActivity : BaseActivity<DatabindingTestModel>() {
    override fun getLayoutId() {
        R.layout.activity_databinding_test
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
