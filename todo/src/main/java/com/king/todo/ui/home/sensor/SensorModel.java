package com.king.todo.ui.home.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.king.todo.R;
import com.king.todo.databinding.FragmentSensorBinding;

import javax.inject.Inject;

import timber.log.Timber;


@ModelView(R.layout.fragment_sensor)
public class SensorModel extends ViewModel<SensorFragment, FragmentSensorBinding> {
    @Inject
    SensorModel() {
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    @Override
    public void attachView(Bundle savedInstanceState, SensorFragment fragment) {
        super.attachView(savedInstanceState, fragment);
//        //获得 硬件控制器
//        try {
//            initSensor();
//        } catch (Exception e) {
//            BaseUtil.toast("没得救了啊！！！！"+e);
//            e.printStackTrace();
//        }
    }

    private void initSensor() {
        sensorManager = (SensorManager) App.getCurrentActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Timber.i(
                        " float x===>" + event.values[0] +
                                "float y===>" + event.values[1] +
                                "float z===>" + event.values[2]
                );
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void onPause() {

    }

    public void onDestroy() {
        if(sensorManager!=null)
        sensorManager.unregisterListener(sensorEventListener);
        super.onDestroy();
    }
}
