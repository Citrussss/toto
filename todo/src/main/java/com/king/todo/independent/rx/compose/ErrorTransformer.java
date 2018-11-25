package com.king.todo.independent.rx.compose;

import com.king.todo.inject.data.api.ApiInfoEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/4 4:15 PM
 * 只有编译器可能不骗你。
 */
public class ErrorTransformer<T> implements ObservableTransformer<ApiInfoEntity<T>, T> {
    @Override
    public ObservableSource<T> apply(Observable<ApiInfoEntity<T>> upstream) {
        return upstream.flatMap(this::tryError);
    }

    private Observable<T> tryError(ApiInfoEntity<T> infoEntity) {
        int code = infoEntity.getCode();
        switch (code) {
            case 10:
                return Observable.error(new Throwable(infoEntity.getMsg()));
        }
        return Observable.just(infoEntity.getData());
    }

}
