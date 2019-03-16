package com.union.bangbang.todokotlin.base.okhttp

import com.union.bangbang.todokotlin.base.data.pojo.InfoEntity
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/16 4:12 PM
 * 只有编译器可能不骗你。
 */
class ErrorTransform<Upstream : InfoEntity<*>> : ObservableTransformer<Upstream, Upstream> {
    /**
     * Applies a function to the upstream Observable and returns an ObservableSource with
     * optionally different element type.
     * @param upstream the upstream Observable instance
     * @return the transformed ObservableSource instance
     */
    override fun apply(upstream: Observable<Upstream>): ObservableSource<Upstream> {
        return upstream.doOnNext {
            if (it.code != 0) throw ApiException(it.code, it.msg)
        }
    }

}