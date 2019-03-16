package com.union.bangbang.todokotlin.base.okhttp

import android.util.Log
import com.union.bangbang.todokotlin.base.model.BaseModel
import com.union.bangbang.todokotlin.base.utils.ToastUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.lang.ref.WeakReference


/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/15 10:37 PM
 * 只有编译器可能不骗你。
 */
class Reaper<T>(
        model: BaseModel,
        val onNext: Consumer<T> = Consumer { Log.v(this.TAG, it.toString()) },
        val onError: Consumer<Throwable> = Consumer { it?.message?.let { ToastUtil::error } }
) : Observer<T> {
    lateinit var d: Disposable
    lateinit var weekModel: WeakReference<BaseModel>

    companion object {
        val TAG = "Reaper"
    }

    init {
        weekModel=WeakReference(model)
    }

    /**
     * Notifies the Observer that the [Observable] has finished sending push-based notifications.
     *
     *
     * The [Observable] will not call this method if it calls [.onError].
     */
    override fun onComplete() {
        Log.v(TAG, "onComplete")

    }

    /**
     * Provides the Observer with the means of cancelling (disposing) the
     * connection (channel) with the Observable in both
     * synchronous (from within [.onNext]) and asynchronous manner.
     * @param d the Disposable instance whose [Disposable.dispose] can
     * be called anytime to cancel the connection
     * @since 2.0
     */
    override fun onSubscribe(d: Disposable) {
        weekModel.get()?.addDisposable(d)
        Log.v(TAG, "onSubscribe")
    }

    /**
     * Provides the Observer with a new item to observe.
     *
     *
     * The [Observable] may call this method 0 or more times.
     *
     *
     * The `Observable` will not call this method again after it calls either [.onComplete] or
     * [.onError].
     *
     * @param t
     * the item emitted by the Observable
     */
    override fun onNext(t: T) {
        onNext.accept(t)
    }

    /**
     * Notifies the Observer that the [Observable] has experienced an error condition.
     *
     *
     * If the [Observable] calls this method, it will not thereafter call [.onNext] or
     * [.onComplete].
     *
     * @param e
     * the exception encountered by the Observable
     */
    override fun onError(e: Throwable) {
        onError.accept(e)
    }
}


