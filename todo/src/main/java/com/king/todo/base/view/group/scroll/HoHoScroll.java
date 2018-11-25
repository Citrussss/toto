package com.king.todo.base.view.group.scroll;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * @name money
 * @class name：com.king.todo.base.view.group.scroll
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/25 10:05 PM
 * @change
 * @chang time
 * @class describe
 */
public class HoHoScroll extends HorizontalScrollView {
    public HoHoScroll(Context context) {
        super(context);
    }

    public HoHoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HoHoScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HoHoScroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
