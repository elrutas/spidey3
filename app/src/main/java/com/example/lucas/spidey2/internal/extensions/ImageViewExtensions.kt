package com.example.lucas.spidey2.internal.extensions

import android.widget.ImageView
import com.example.lucas.spidey2.internal.utils.GlideApp

fun ImageView.loadUrl(url: String) {
    GlideApp.with(this)
            .load(url)
            .fitCenter()
            .into(this)
}