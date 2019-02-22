package com.union.bangbang.zero.util.photo;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.union.bangbang.zero.util.JimUtils;
import com.union.bangbang.zero.util.KeyBoardUtil;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Rabies
 *
 * @author USER
 * Date:   2019-02-22
 * Time:   14:15
 */
public class TimePickerHelper {
    public static TimePickerView onSelectTimeClick(View view, OnTimeSelectListener listener, String format) {
        return onSelectTimeClick(view, listener, format,true);
    };
    public static TimePickerView onSelectTimeClick(View view, OnTimeSelectListener listener, String format,boolean autoFill) {
        KeyBoardUtil.hideInputMethod(view);
        TimePickerView pickerView;
        Calendar calendar = Calendar.getInstance();
        pickerView = (TimePickerView) view.getTag(view.getId());
        if (pickerView == null) {
            pickerView = new TimePickerBuilder(view.getContext(), (date, v) -> {
                if(autoFill){
                    ((TextView) view).setText(DateFormatUtils.format(date, TextUtils.isEmpty(format) ? "yyyy.MM.dd - HH" : format));
                }
                if (listener != null) {
                    listener.onTimeSelect(date, view);
                }
            })
//                    .setTimeSelectChangeListener(date -> { ((TimePickerView) view.getTag(view.getId())).setTitleText(TimeUtil.getWeekOfDate(TimeUtil.getDate(date.getTime()))); })
                    .setDate(calendar)//设置默认时间 创建的时候是默认的 编辑的时候进行数据还原
                    .setLabel("", "", "", //                    .setTitleText(TimeUtil.getWeekOfDate(new Date()))
                            "", "", "")
                    .setType(new boolean[]{true, true, true, true, true, true})
                    .build();
            view.setTag(view.getId(), pickerView);
        }
        return pickerView;
    }
}
