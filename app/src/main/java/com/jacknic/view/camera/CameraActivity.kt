package com.jacknic.view.camera

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams
import com.jacknic.view.R
import kotlin.math.abs

class CameraActivity : AppCompatActivity() {

    private var animator: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            clipChildren = false
        }
        val seekBar = SeekBar(this).apply {
            layout.addView(this, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            max = 360
        }

        val cameraImageView = CameraImageView(this).apply {
            layout.addView(this)
            setBackgroundColor(Color.YELLOW)
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                    topMargin = 200
                }
            setImageResource(R.mipmap.ic_launcher)
        }
        cameraImageView.setOnClickListener {
            var startValue = 0f
            var endValue = 360f
            if (cameraImageView.progress != 0f) {
                endValue = 0f
                startValue = cameraImageView.progress
            }
            animator?.cancel()
            animator = ValueAnimator.ofFloat(startValue, endValue)
                .apply {
                    val length = ((endValue - startValue) / 360 * 1500L).toLong()
                    duration = abs(length)
                    addUpdateListener {
                        val value = it.animatedValue as Float
                        cameraImageView.progress = value
                        seekBar.progress = value.toInt()
                    }
                    interpolator = AccelerateDecelerateInterpolator()
                }
            animator?.start()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                cameraImageView.progress = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        setContentView(layout)
    }
}