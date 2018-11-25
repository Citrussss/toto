package com.king.todo.ui.user.register;


import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.king.todo.R;
import com.king.todo.base.utils.FunnyToast;
import com.king.todo.databinding.ActivityRegisterBinding;
import com.king.todo.independent.rx.compose.ErrorTransformer;
import com.king.todo.inject.data.api.Api;
import com.king.todo.ui.user.UserEntity;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @name money
 * @class name：com.king.todo.ui.user.register
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/17 9:00 PM
 * @change
 * @chang time
 * @class describe
 */
@ModelView(R.layout.activity_register)
public class RegisterModel extends ViewModel<RegisterActivity,ActivityRegisterBinding> {
    @Inject RegisterModel(){}
    private UserEntity userEntity;
    @Inject
    Api api;
    @Override
    public void attachView(Bundle savedInstanceState, RegisterActivity activity) {
        super.attachView(savedInstanceState, activity);
        userEntity=new UserEntity();
        getDataBinding().setParams(userEntity);
    }
    public void onRigisterClick(View view){
        Disposable subscribe = api.register(userEntity)
                .compose(new ErrorTransformer<>())
                .subscribe(FunnyToast::message, FunnyToast::error);
    }
}
