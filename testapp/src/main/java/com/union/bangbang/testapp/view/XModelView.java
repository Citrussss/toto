package com.union.bangbang.testapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.union.bangbang.testapp.R;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019-01-28 20:08
 * 只有编译器可能不骗你。
 */
public class XModelView extends View {
    private Paint circlePaint = new Paint();
    private Paint rectPaint = new Paint();
    private Path circlePath = new Path(), rectPath = new Path();
    private RectF rectF = new RectF();

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public XModelView(Context context) {
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
    public XModelView(Context context, AttributeSet attrs) {
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
        super.onDraw(canvas);
        Bitmap drawingCache = getDrawingCache();
        canvas.saveLayer(0,0,getRight(),getBottom(),null,Canvas.ALL_SAVE_FLAG);
//        canvas.drawPath(circlePath, rectPaint);
        canvas.drawPath(rectPath, rectPaint);
        canvas.drawBitmap(drawingCache, new Matrix(),circlePaint);
        canvas.restore();
//        setBackground(null);
    }

    public void init(Context context) {
        circlePaint.setAntiAlias(true);
        circlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setStrokeWidth(10);
        circlePaint.setColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? context.getColor(R.color.colorAccent) : context.getResources().getColor(R.color.colorAccent));

        rectPaint.setAntiAlias(true);
//        rectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        rectPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        rectPaint.setStrokeWidth(10);
        rectPaint.setColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? context.getColor(R.color.colorPrimary) : context.getResources().getColor(R.color.colorPrimary));
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w    Current width of this view.
     * @param h    Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.left = getPaddingLeft();
        rectF.top = getPaddingTop();
        rectF.right = w - getPaddingRight();
        rectF.bottom = h - getPaddingBottom();
        float size = (rectF.right - rectF.left) / 2;
        circlePath.addCircle(rectF.left + size, rectF.top + size, (rectF.right - rectF.left) / 4, Path.Direction.CCW);
        rectPath.addRect(new RectF(rectF.left, rectF.top, (rectF.left + rectF.right) / 2, (rectF.top + rectF.bottom) / 2), Path.Direction.CCW);
    }

}
