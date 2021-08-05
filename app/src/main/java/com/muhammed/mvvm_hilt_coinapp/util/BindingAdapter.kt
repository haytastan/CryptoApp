package com.muhammed.mvvm_hilt_coinapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muhammed.mvvm_hilt_coinapp.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view).load(url).into(view)
}

@BindingAdapter("coinBackGround")
fun loadBackGround(textView: TextView, number: String?) {
    val firstIndex = number?.substring(0, 1)
    if (firstIndex == "-") {
        DrawableCompat.setTint(
            textView.background,
            ContextCompat.getColor(textView.context, R.color.red)
        )
    } else {
        DrawableCompat.setTint(
            textView.background,
            ContextCompat.getColor(textView.context, R.color.green)
        )
    }
}