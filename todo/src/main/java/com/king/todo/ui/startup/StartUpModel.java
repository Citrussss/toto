package com.king.todo.ui.startup;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.binding.model.Config;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.king.todo.R;
import com.king.todo.base.arouter.ArouterUtil;
import com.king.todo.databinding.ActivityStartupBinding;

import javax.inject.Inject;

import me.goldze.mvvmhabit.base.BaseViewModel;

import static com.king.todo.inject.component.ActivityComponent.Router.login;

public class StartUpModel extends BaseViewModel {
    public StartUpModel(@NonNull Application application) {
        super(application);
    }
}
