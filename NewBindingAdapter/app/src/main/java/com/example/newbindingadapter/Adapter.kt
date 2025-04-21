package com.example.newbindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageSrc")
fun setImage(view: ImageView, count: Int) {
    val img =
        if (count == 1) R.drawable.grat
        else if (count == 2) R.drawable.premium
        else if (count == 3) R.drawable.ic_launcher_background
        else if (count == 4) R.drawable.ic_launcher_foreground
        else R.drawable.www
    view.setImageResource(img)
}

