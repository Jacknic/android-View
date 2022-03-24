package com.jacknic.view.bevel

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlin.math.tan

/**
 * 圆角斜边卡片
 *
 * @author Jacknic
 */
class BevelRoundCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val mMaskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var maskBitmap: Bitmap
    private lateinit var rect: Rect

    var radius = 16f
        set(value) {
            field = value
            drawMask()
            postInvalidate()
        }
    var degree = 8f
        set(value) {
            field = value
            drawMask()
            postInvalidate()
        }

    init {
        mMaskPaint.color = Color.WHITE
        mMaskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    }

    override fun draw(canvas: Canvas) {
        val saveLayer = canvas.saveLayer(null, null)
        super.draw(canvas)
        canvas.drawBitmap(maskBitmap, rect, rect, mMaskPaint)
        canvas.restoreToCount(saveLayer)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawMask()
    }

    /**
     * 绘制蒙版
     */
    private fun drawMask() {
        rect = Rect(0, 0, width, height)

        val path = Path()
        val indent = tan(degree / 90) * height
        mPaint.pathEffect = CornerPathEffect(radius)
        path.moveTo(indent + radius, 0f)
        path.lineTo(width.toFloat(), 0f)
        path.lineTo(width - indent - radius, height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.lineTo(indent + radius, 0f)
        path.close()

        maskBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(maskBitmap)
        canvas.drawPath(path, mPaint)
    }
}