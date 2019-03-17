package com.union.bangbang.todokotlin.base.okhttp

import com.union.bangbang.todokotlin.base.data.pojo.InfoEntity
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import org.json.JSONException
import retrofit2.HttpException
import java.util.*

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/16 4:12 PM
 * 只有编译器可能不骗你。
 */
class ErrorTransform<Body> : ObservableTransformer<Body, Body> {
    /**
     * Applies a function to the upstream Observable and returns an ObservableSource with
     * optionally different element type.
     * @param upstream the upstream Observable instance
     * @return the transformed ObservableSource instance
     */
    override fun apply(upstream: Observable<Body>): ObservableSource<Body> {
        return upstream
                .onErrorResumeNext(this::onThrowable)
                .doOnNext {
                    if (it is InfoEntity<*> && it.code != 0) throw ApiException(it.code, it.msg)
                }
    }

    private fun onThrowable(throwable: Throwable): Observable<Body> {
        val errorMessage: String
        val code: Int
        when (throwable) {
            is HttpException -> {
                code = throwable.code()
                errorMessage = when (code) {
                    401 -> "token无效:" + throwable.message()
                    402 -> "数据库连接错误:" + throwable.message()
                    403 -> "无记录:" + throwable.message()
                    405 -> "token无效:" + throwable.message()
                    400 -> "参数为空:" + throwable.message()
                    else -> "未知错误:" + throwable.message()
                }
                throw ApiException(code, errorMessage)
            }
            is ServiceConfigurationError -> throw ApiException(0, "服务器错误")
            is JSONException -> throw ApiException(0, "数据解析错误")
            else -> return Observable.error<Body>(throwable)
        }
    }
}