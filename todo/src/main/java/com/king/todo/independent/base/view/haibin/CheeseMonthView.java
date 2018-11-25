package com.king.todo.independent.base.view.haibin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;
import com.king.todo.R;
import com.union.bangbang.zero.AppUtil;

import static com.binding.model.App.getCurrentActivity;

public class CheeseMonthView extends MonthView {
    private Paint currentMonthTextPaint;
    private Paint notCurrentMonthTexrPaint;
    private int textBlack = 0xff333333;
    private int textGray = 0xff999999;
    private int yellow = 0xFFF4B33F;

    public CheeseMonthView(Context context) {
        super(context);
        initColor();
        initPaint();
    }

    /**
     * 绘制选中的日子
     *
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param y         日历Card y起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return 返回true 则绘制onDrawScheme，因为这里背景色不是互斥的，所以返回true
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        float width = x + mItemWidth / 2;
        float height = y + mItemHeight / 2 - mesureTextHeght() / 2 + currentMonthTextPaint.getFontMetrics().bottom;
        canvas.drawCircle(width, height, (float) (currentMonthTextPaint.measureText("30") * 1.5), notCurrentMonthTexrPaint);
        return false;
    }

    /**
     * 绘制标记的事件日子
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     * @param y        日历Card y起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
//        canvas.drawText(String.valueOf(calendar.getDay()), x, y, textPaint);
        float width = x + mItemWidth / 2;
        float height = y + mItemHeight / 2 + mesureTextHeght();
        canvas.drawCircle(width, height, 5, currentMonthTextPaint);
    }

    /**
     * 绘制文本
     *
     * @param canvas     canvas
     * @param calendar   日历calendar
     * @param x          日历Card x起点坐标
     * @param y          日历Card y起点坐标
     * @param hasScheme  是否是标记的日期
     * @param isSelected 是否选中
     */
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        canvas.drawText(String.valueOf(calendar.getDay()), x + mItemWidth / 2, y + mItemHeight / 2, calendar.isCurrentMonth() ? currentMonthTextPaint : notCurrentMonthTexrPaint);
    }

    private void initPaint() {
        currentMonthTextPaint = new Paint();
        currentMonthTextPaint.setAntiAlias(true);
        currentMonthTextPaint.setTextAlign(Paint.Align.CENTER);
        currentMonthTextPaint.setColor(textBlack);
        currentMonthTextPaint.setFakeBoldText(true);
        currentMonthTextPaint.setTextSize(dip2px(AppUtil.peekActivity(), 12));

        notCurrentMonthTexrPaint = new Paint();
        notCurrentMonthTexrPaint.setAntiAlias(true);
        notCurrentMonthTexrPaint.setTextAlign(Paint.Align.CENTER);
        notCurrentMonthTexrPaint.setColor(textGray);
        notCurrentMonthTexrPaint.setFakeBoldText(true);
        notCurrentMonthTexrPaint.setTextSize(dip2px(AppUtil.peekActivity(), 12));
    }

    private void initColor() {
//        indigoBlue = ContextCompat.getColor(AppUtil.peekActivity(), R.color.colorPrimary);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private float mesureTextHeght() {
        Paint.FontMetrics fontMetrics = currentMonthTextPaint.getFontMetrics();
//        return fontMetrics.descent - fontMetrics.ascent;
        return fontMetrics.bottom - fontMetrics.top;
    }
}
