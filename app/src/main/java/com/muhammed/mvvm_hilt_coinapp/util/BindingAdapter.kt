package com.muhammed.mvvm_hilt_coinapp.util

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muhammed.mvvm_hilt_coinapp.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view).load(url).into(view)
}

@BindingAdapter("coinBackground")
fun loadBackground(textView: TextView, number: String?) {
    val firstIndex = number?.substring(0, 1)
    if (firstIndex == "-") {
        textView.setTextColor(Color.parseColor("#FF0000"))
    } else {

        textView.setTextColor(Color.parseColor("#00FF40"))

    }
}
