package com.king.todo.ui.home.calendar;

import com.binding.model.model.ViewInflateRecycler;
import com.haibin.calendarview.Calendar;
import com.union.bangbang.zero.util.DateUtil;

import java.util.Date;

import static com.union.bangbang.zero.util.DateUtil.getDay;
import static com.union.bangbang.zero.util.DateUtil.getMonth;
import static com.union.bangbang.zero.util.DateUtil.getYear;


/**
 * @name money
 * @class name：com.king.todo.ui.home_navigation.calendar
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/18 9:35 PM
 * @change
 * @chang time
 * @class describe
 */
public class CalendarViewDay extends ViewInflateRecycler {

    private long timestamp;

    public CalendarViewDay(long timestamp) {
        this.timestamp = timestamp;
    }

    public Calendar getSchemeCalendar(){
        Date date = DateUtil.getDate(timestamp);
        return getSchemeCalendar(getYear(date),getMonth(date),getDay(date),0xFF40db25,"测"+timestamp);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

}
