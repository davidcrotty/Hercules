package net.davidcrotty.hercules.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import net.davidcrotty.hercules.R

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class PieProgressView : FrameLayout {

    private lateinit var paint: Paint
    private var shape: RectF? = null
    private var currentCircleDraw: Float = 0f

    var progress: Int = 0
        set(value) {
            field = value
            val percentage = (value.toFloat() / max.toFloat())
            currentCircleDraw = percentage * 360f
            invalidate()
        }
    var max: Int = 0
        set(value)  {
            field = value
            invalidate()
        }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.rep_count_down, this)
        paint = Paint()
        paint.color = ContextCompat.getColor(context, R.color.lightGreen)
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if(shape == null || changed) {
            shape = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        if(max <= 0) return
        shape?.let {
            canvas?.drawArc(it, 270f, currentCircleDraw, true, paint)
        }
    }
}