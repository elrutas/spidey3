package com.example.lucas.spidey2.ui.features.comiclist

import com.example.lucas.spidey2.domain.model.Comic

interface ComicListView {

    fun showComics(comics: List<Comic>)
}