package com.jacknic.view.rotate3d

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * 沿 Y 轴中心旋转
 */
class Rotate3dAnimation @JvmOverloads constructor(
    private val fromDegree: Float = 0f,
    private val toDegree: Float = 360f
) : Animation() {

    private var centerX = 0f
    private var centerY = 0f
    private val camera = Camera()

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        centerX = width / 2f
        centerY = height / 2f
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val degree = fromDegree + (toDegree - fromDegree) * interpolatedTime
        camera.save()
        camera.rotateY(degree)
        camera.rotateX(degree)
        camera.rotateZ(degree / 3)
        camera.getMatrix(t.matrix)
        camera.restore()

        t.matrix.preTranslate(-centerX, -centerY)
        t.matrix.postTranslate(centerX, centerY)
    }
}