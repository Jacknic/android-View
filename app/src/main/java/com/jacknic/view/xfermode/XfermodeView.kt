package com.jacknic.view.xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 * 自定义视图 xfermode
 *
 * @author Jacknic
 */
class XfermodeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaintCircle = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintCube = Paint(Paint.ANTI_ALIAS_FLAG)

    var mode = PorterDuff.Mode.SRC_OVER
        set(value) {
            field = value
            mPaintCube.xfermode = PorterDuffXfermode(mode)
            postInvalidate()
        }

    init {
        mPaintCircle.color = Color.BLUE
        mPaintCube.color = Color.RED
        mPaintCube.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        val dx = width / 2f
        val dy = height / 2f
        canvas.translate(dx, dy)
        val radius = min(dx, dy) / 2
        canvas.drawCircle(0f, 0f, radius, mPaintCircle)
        canvas.drawRect(0f, 0f, radius, radius, mPaintCube)
    }
}