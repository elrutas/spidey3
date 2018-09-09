package com.example.lucas.spidey2.extensions

import android.widget.ImageView
import com.example.lucas.spidey2.utils.GlideApp

fun ImageView.loadUrl(url: String) {
    GlideApp.with(this)
            .load(url)
            .fitCenter()
            .into(this)
}