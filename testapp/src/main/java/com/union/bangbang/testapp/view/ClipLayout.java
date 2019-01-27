package com.union.bangbang.testapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.union.bangbang.testapp.R;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019-01-27 19:14
 * 只有编译器可能不骗你。
 */
public class ClipLayout extends FrameLayout {
    // 1. 定义圆角信息 和 path
    private float[] radii = new float[8];   // top-left, top-right, bottom-right, bottom-left
    private Path path;
    private RectF rectF=new RectF();
    private Paint paint=new Paint();
    private final String TAG ="ClipLayout";
    public ClipLayout(Context context) {
        this(context,null);
    }

    public ClipLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs){
        radii[0]=30;
        radii[1]=30;
        radii[2]=30;
        radii[3]=30;
        radii[4]=30;
        radii[5]=30;
        radii[6]=30;
        radii[7]=30;
        paint.setStrokeWidth(10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            paint.setColor(getContext().getColor(R.color.colorAccent));
        }else {
            paint.setColor(getContext().getResources().getColor(R.color.colorAccent));
        }
// 2. 通过自定义属性获取圆角信息(以左上角为例)
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RCRelativeLayout);
//        mRoundCorner = ta.getDimensionPixelSize(R.styleable.RCRelativeLayout_round_corner, 0);
//        radii[0] = mRoundCornerTopLeft;
//        radii[1] = mRoundCornerTopLeft;

// 3. 创建空的Path
        path = new Path();
        Log.i(TAG, "init: ");

    }    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.left = getPaddingLeft();
        rectF.top = getPaddingTop();
        rectF.right = w - getPaddingRight();
        rectF.bottom = h - getPaddingBottom();
        path.reset();
        path.addRoundRect(rectF, radii, Path.Direction.CW);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.FILL);
        Log.i(TAG, "onSizeChanged: "+rectF.toString());
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i(TAG, "dispatchDraw: ");
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas
                .ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.drawPath(path,paint);
        canvas.restore();

    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Log.i(TAG, "drawChild: ");
//        canvas.drawPath(path,paint);
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.clipPath(path);
        Log.i(TAG, "onDraw: ");
//        canvas.drawPath(path,paint);
        super.onDraw(canvas);
    }
}
