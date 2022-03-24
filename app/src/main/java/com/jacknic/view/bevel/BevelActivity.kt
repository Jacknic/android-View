package com.jacknic.view.bevel

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.jacknic.view.R

/**
 * 圆角斜边卡片布局
 *
 * @author Jacknic
 */
class BevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
        val bevelRoundCardView = BevelRoundCardView(this)
        bevelRoundCardView.setBackgroundColor(Color.BLUE)
        layout.addView(bevelRoundCardView, 300, 300)
        val imageView = ImageView(this)
        imageView.setImageResource(R.mipmap.ic_launcher)
        imageView.setBackgroundColor(Color.GREEN)
        bevelRoundCardView.addView(imageView, 300, 300)

        for (i in 1..2) {
            val isRadius = i == 1
            SeekBar(this).apply {
                layout.addView(
                    this,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                max = 360
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        val value = progress.toFloat()
                        if (isRadius) {
                            bevelRoundCardView.radius = value
                        } else {
                            bevelRoundCardView.degree = value
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
            }
        }
        setContentView(layout)
    }
}