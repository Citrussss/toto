package com.king.todo.ui.startup;


import android.os.Bundle;

import com.binding.model.Config;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.king.todo.R;
import com.king.todo.base.arouter.ArouterUtil;
import com.king.todo.databinding.ActivityStartupBinding;

import javax.inject.Inject;

import static com.king.todo.inject.component.ActivityComponent.Router.login;

@ModelView(R.layout.activity_startup)
public class StartUpModel extends ViewModel<StartUpActivity, ActivityStartupBinding> {
    @Inject
    StartUpModel() {
    }

    @Override
    public void attachView(Bundle savedInstanceState, StartUpActivity activity) {
        super.attachView(savedInstanceState, activity);
//        Intent intent = new Intent();
//        intent.setClass(getT(), HomeActivity.class);
//        getT().startActivity(intent);
        Bundle bundle =new Bundle();
        bundle.putString(Config.title,"用户登录");
        ArouterUtil.navigation(login,bundle);
        finish();
    }
}
