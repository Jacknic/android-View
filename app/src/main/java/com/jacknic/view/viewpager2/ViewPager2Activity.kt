package com.jacknic.view.viewpager2

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * ViewPager2 的基础使用
 *
 * @author Jacknic
 */
class ViewPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPager2 = ViewPager2(this)
        viewPager2.adapter = TextListAdapter().also {
            it.submitList((1..10).toList())
        }
        viewPager2.setCurrentItem(4, false)
        setContentView(viewPager2)
    }
}

internal class TextViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

internal class TextListAdapter : ListAdapter<Number, TextViewHolder>(object :
    DiffUtil.ItemCallback<Number>() {
    override fun areItemsTheSame(oldItem: Number, newItem: Number): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Number, newItem: Number): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val tv = TextView(parent.context).apply {
            gravity = Gravity.CENTER
            layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
        return TextViewHolder(tv)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val item = getItem(position)
        holder.textView.text = item.toString()
    }
}