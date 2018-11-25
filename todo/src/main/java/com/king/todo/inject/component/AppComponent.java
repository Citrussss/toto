package com.king.todo.inject.component;

import android.content.Context;

import com.king.todo.inject.data.api.Api;
import com.king.todo.inject.data.sql.DatabaseApi;
import com.king.todo.inject.module.AppModule;
import com.king.todo.inject.module.DataModule;
import com.king.todo.inject.module.NetWorkModule;
import com.king.todo.inject.qualifier.context.AppContext;
import com.king.todo.inject.scope.ApplicationScope;
import com.king.todo.ui.MoneyApplication;

import dagger.Component;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:08
 * modify developer：  admin
 * modify time：10:08
 * modify remark：
 *
 * @version 2.0
 */

@ApplicationScope
@Component(modules={AppModule.class, NetWorkModule.class,DataModule.class})
public interface AppComponent {
    @AppContext Context context();
    Api getApi();
    DatabaseApi getDatabaseApi();
    void inject(MoneyApplication application);
}
