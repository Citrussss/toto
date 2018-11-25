package com.king.todo.inject.component;

import com.king.todo.inject.module.FragmentModule;
import com.king.todo.inject.scope.FragmentScope;
import com.king.todo.ui.home.calendar.CalendarFragment;
import com.king.todo.ui.home.record.RecordFragment;
import com.king.todo.ui.home.sensor.SensorFragment;

import dagger.Component;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:29
 * modify developer：  admin
 * modify time：11:29
 * modify remark：
 *
 * @version 2.0
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules={FragmentModule.class})
public interface FragmentComponent {
    void inject(RecordFragment fragment);
    void inject(CalendarFragment fragment);
    void inject(SensorFragment fragment);
}
