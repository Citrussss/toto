package com.king.todo.inject.component;

/**
 * Created by pc on 2017/9/4.
 */

import com.king.todo.inject.module.ServiceModule;
import com.king.todo.inject.scope.ServiceScope;

import dagger.Component;

@ServiceScope
@Component(dependencies = AppComponent.class, modules = {ServiceModule.class})
public interface ServiceComponent {
//    void inject(UpdateService service);
//    void inject(ResourceService service);
}
