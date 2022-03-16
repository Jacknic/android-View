package com.jacknic.view.camera

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * 3D 旋转视图
 */
class CameraImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mMatrix = Matrix()
    private val camera = Camera()
    var progress = 0f
        set(value) {
            field = value
            postInvalidate()
        }

    override fun onDraw(canvas: Canvas) {
        // 保存画布与相机状态
        canvas.save()
        camera.save()

        // 移动 Camera 并沿Y轴旋转
        camera.translate(0f, 0f, -progress)
        camera.rotateY(progress)
        camera.applyToCanvas(canvas)

        // 使绘制内容居中
        camera.getMatrix(mMatrix)
        val centerX = width / 2f
        val centerY = height / 2f
        mMatrix.preTranslate(-centerX, -centerY)
        mMatrix.postTranslate(centerX, centerY)
        canvas.setMatrix(mMatrix)

        // 绘制控件内容及恢复状态
        super.onDraw(canvas)
        camera.restore()
        canvas.restore()
        canvas.drawText("%.1f".format(progress), 0f, 0f, mPaint)
    }
}