package com.union.bangbang.todokotlin.ui.clock.alarm

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.union.bangbang.todokotlin.R
import com.union.bangbang.todokotlin.base.utils.getColor
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/28 7:44 PM
 * 沉着冷静面对⛈️
 */
class RippleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr), Runnable {

    override fun run() {
        val copy = ArrayList(circleList)
        circleList.clear()
        for (entity in copy) {
            entity.alpha--
            entity.size--
            if (entity.alpha != 0 && entity.size != 0F) circleList.add(entity)
            else cache[entity.hashCode()] = entity
        }
        if (circleList.isNotEmpty()) postInvalidate()
    }

    private val timePaint: Paint by lazy {
        val paint = Paint()
        paint.color = getColor(R.color.white)
        paint.strokeWidth = 10F
        paint.style = Paint.Style.STROKE
        return@lazy paint
    }
    private val popPaint: Paint by lazy {
        val paint = Paint()
        paint.color = getColor(R.color.white)
        paint.strokeWidth = 10F
        paint.style = Paint.Style.STROKE
        return@lazy paint
    }
    private val hourPath: Path by lazy { Path() }
    private val minutePath: Path by lazy { Path() }
    private val deviation: Double = Math.PI / 2F
    var size = Math.min(width / 4f, height / 4f)
    var cx = width / 2f
    var cy = height / 2f
    private var hour: Int = 0
    private var minute: Int = 0
    private val circleList: ArrayList<CircleEntity> = ArrayList()
    private val cache = HashMap<Int, CircleEntity>()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        size = Math.min(width / 4f, height / 4f)
        cx = width / 2f
        cy = height / 2f
        canvas?.drawCircle(cx, cy, size, timePaint)
        drawTime(canvas)
        drawHalo(canvas)
    }

    private fun drawHalo(canvas: Canvas?) {
        for (circleEntity in circleList) {
            popPaint.color = circleEntity.color
            popPaint.alpha = circleEntity.alpha
            if (circleEntity.size < 0) {
                circleEntity.size = size
            }
            canvas?.drawCircle(cx, cy, circleEntity.size, popPaint)
        }
        if (!circleList.isEmpty()) {
            handler.postDelayed(this, 10)
        }
    }

    private fun drawTime(canvas: Canvas?) {
        hourPath.reset()
        minutePath.reset()
        hourPath.setLastPoint(cx, cy)
        minutePath.setLastPoint(cx, cy)
        val hR = (hour + minute / 60F) / 6F * Math.PI - deviation
        val mR = minute / 30F * Math.PI - deviation
        minutePath.lineTo((((cx + 0.5 * size * Math.cos(hR)).toFloat())), (cy + 0.5 * size * Math.sin(hR)).toFloat())
        hourPath.lineTo((((cx + size * Math.cos(mR)).toFloat())), (cy + size * Math.sin(mR)).toFloat())
        canvas?.drawPath(hourPath, timePaint)
        canvas?.drawPath(minutePath, timePaint)
    }

    fun setTime(hour: Int? = null, minute: Int? = null) {
        hour?.let { this.hour = it }
        minute?.let { this.minute = minute }
        postInvalidate()
    }

    fun setTime(currentTimeMillis: Long) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTimeMillis
        val time = calendar.time
        setTime(time.hours, time.minutes)
    }

    class CircleEntity constructor(
            var size: Float = 0F,
            var color: Int = 0,
            var alpha: Int = 0) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CircleEntity

            if (color != other.color) return false

            return true
        }

        override fun hashCode(): Int {
            return color
        }
    }

    fun addPop(circleEntity: CircleEntity) {
        circleList.add(circleEntity)
        postInvalidate()
    }

    fun addPop(size: Float, color: Int, alpha: Int) {
        var circleEntity: CircleEntity?
        if (cache[color] == null) {
            circleEntity = CircleEntity(size, color, alpha)
        } else {
            circleEntity = cache[color]
            circleEntity?.size = size
            circleEntity?.alpha = alpha
        }
        circleEntity?.let { addPop(it) }
    }

}
