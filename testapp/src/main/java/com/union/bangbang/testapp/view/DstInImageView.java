package com.union.bangbang.testapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019-01-28 20:08
 * 只有编译器可能不骗你。
 */
public class DstInImageView extends androidx.appcompat.widget.AppCompatImageView {
    private Paint circlePaint = new Paint();
    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public DstInImageView(Context context) {
        this(context, null);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     *
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(Context, AttributeSet, int)
     */
    public DstInImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        setDrawingCacheEnabled(true);
        Bitmap drawingCache = getDrawingCache();
        canvas.saveLayer(0,0,getRight(),getBottom(),null,Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        canvas.drawBitmap(drawingCache, new Matrix(),circlePaint);
        canvas.restore();
    }

    public void init(Context context) {
        circlePaint.setAntiAlias(true);
        circlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        circlePaint.setStrokeWidth(10);
//        circlePaint.setColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? context.getColor(R.color.colorAccent) : context.getResources().getColor(R.color.colorAccent));

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }


}
