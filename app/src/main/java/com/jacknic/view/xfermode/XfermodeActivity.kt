package com.jacknic.view.xfermode

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.jacknic.view.databinding.ActivityXfermodeBinding

/**
 * xfermode 绘制模式视图
 *
 * @author Jacknic
 */
class XfermodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityXfermodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityXfermodeBinding.inflate(layoutInflater)
        val modeValues = PorterDuff.Mode.values()
        modeValues.forEach {
            val radioButton = RadioButton(this)
            radioButton.text = it.name
            radioButton.id = it.ordinal
            binding.modeGroup.addView(
                radioButton,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        binding.modeGroup.check(PorterDuff.Mode.SRC_OVER.ordinal)
        binding.modeGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.xfermodeView.mode = modeValues[checkedId]
        }
        setContentView(binding.root)
    }
}