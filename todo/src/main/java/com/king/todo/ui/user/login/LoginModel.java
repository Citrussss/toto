package com.king.todo.ui.user.login;


import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.king.todo.R;
import com.king.todo.base.arouter.ArouterUtil;
import com.king.todo.base.utils.FunnyToast;
import com.king.todo.databinding.ActivityLoginBinding;
import com.king.todo.independent.rx.compose.ErrorTransformer;
import com.king.todo.inject.data.api.Api;
import com.king.todo.ui.user.UserEntity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.king.todo.inject.component.ActivityComponent.Router.home;
import static com.king.todo.inject.component.ActivityComponent.Router.rigister;

/**
 * @name money
 * @class name：com.king.todo.ui.user.login
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/4 12:45 PM
 * @change
 * @chang time
 * @class describe
 */
@ModelView(R.layout.activity_login)
public class LoginModel extends ViewHttpModel<LoginActivity, ActivityLoginBinding, UserEntity> {
    @Inject
    LoginModel() {
    }

    public String loginBg = "http://kk.51.com/refer?url=https://mmbiz.qpic.cn/mmbiz_gif/BOeKkc9wvYe92nLf0lTPMMLNmZP3gWK5iaDTZoQWcveThSk4XfWAia7QpmArbiaX0V1NiaWF027OKPMLU8mdmiaDKEg/640?wx_fmt=gif";
    @Inject
    Api api;
    private SpringSystem springSystem;
    private Spring spring;

    @Override
    public void attachView(Bundle savedInstanceState, LoginActivity loginActivity) {
        super.attachView(savedInstanceState, loginActivity);
        getDataBinding().setParams(new UserEntity());
    }

    public void onLoginClick(View view) {
        addDisposable(api.login(getDataBinding().getParams())
                .compose(new ErrorTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(FunnyToast::message, FunnyToast::error));
    }

    public void onRegisterClick(View view) {
        ArouterUtil.navigation(rigister);
    }


    @Override
    public void onNext(UserEntity userEntity) {

    }

    public void onSkipClick(View view) {
        ArouterUtil.navigation(home);
//        finish();
    }

    public void onTestClick(View view) {
        Observable.just(1)
                .doOnNext(integer -> {
                    view.animate().scaleX(0).setDuration(1000).start();
                })
                .delay(1,TimeUnit.SECONDS)
                .subscribe(integer -> {
            view.animate().scaleX(1f).setDuration(1000).setStartDelay(1000).start();
        });
    }
}
