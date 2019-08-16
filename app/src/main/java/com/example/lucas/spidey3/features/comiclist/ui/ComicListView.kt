package com.example.lucas.spidey3.features.comiclist.ui

import android.widget.ImageView
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicListItem
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicPM

interface ComicListView {

    fun showComics(items: List<ComicListItem>)

    fun launchDetailActivity(comicPM: ComicPM, imageViewForAnimation: ImageView)
}