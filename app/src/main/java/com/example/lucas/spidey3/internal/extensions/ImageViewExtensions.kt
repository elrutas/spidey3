package com.example.lucas.spidey3.internal.extensions

import android.widget.ImageView
import com.example.lucas.spidey3.internal.utils.GlideApp

fun ImageView.loadUrl(url: String) {
    GlideApp.with(this)
            .load(url.replace("http", "https"))
            .fitCenter()
            .into(this)
}