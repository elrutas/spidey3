package com.example.lucas.spidey2.ui.features.comiclist

import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicListItem

interface ComicListView {

    fun showComics(items: List<ComicListItem>)
}