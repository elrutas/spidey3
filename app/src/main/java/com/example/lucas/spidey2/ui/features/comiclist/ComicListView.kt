package com.example.lucas.spidey2.ui.features.comiclist

import android.widget.ImageView
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicListItem
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicPM

interface ComicListView {

    fun showComics(items: List<ComicListItem>)

    fun launchDetailActivity(comicPM: ComicPM, imageViewForAnimation: ImageView)
}