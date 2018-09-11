package com.example.lucas.spidey2.ui.features.comicdetail

import com.example.lucas.spidey2.domain.model.Comic

interface ComicDetailView {

    fun showComic(comic: Comic)
}