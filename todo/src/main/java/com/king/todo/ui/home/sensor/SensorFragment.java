package com.king.todo.ui.home.sensor;

import com.king.todo.base.cycle.BaseFragment;


public class SensorFragment extends BaseFragment<SensorModel> {

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        vm.onDestroy();
    }
}
