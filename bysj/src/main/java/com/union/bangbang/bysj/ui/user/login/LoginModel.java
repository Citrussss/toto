package com.union.bangbang.bysj.ui.user.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019/1/5 4:16 PM
 * 只有编译器可能不骗你。
 */
public class LoginModel extends BaseViewModel {
    public LoginModel(@NonNull Application application) {
        super(application);
    }
    public ObservableField<String> mobile=new ObservableField<>();
    public ObservableField<String> password=new ObservableField<>();
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Log.i("touch", "call: ");
        }
    });
}
