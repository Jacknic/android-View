package com.jacknic.view.rotate3d

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.jacknic.view.R

/**
 * 通过自定义 Animation 类实现视图 3D 旋转
 */
class Rotate3dActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            clipChildren = false
        }

        val imageView = ImageView(this).apply {
            layout.addView(this)
            layoutParams =
                LinearLayoutCompat.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                    topMargin = 200
                }
            setImageResource(R.mipmap.ic_launcher)
        }
        imageView.setOnClickListener {
            val rotate3dAnimation = Rotate3dAnimation()
            rotate3dAnimation.duration = 3000
            rotate3dAnimation.fillAfter = true
            it.startAnimation(rotate3dAnimation)
        }
        setContentView(layout)
    }
}