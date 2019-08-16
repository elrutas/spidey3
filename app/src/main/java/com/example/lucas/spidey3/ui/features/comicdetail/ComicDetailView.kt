package com.example.lucas.spidey3.ui.features.comicdetail

import com.example.lucas.spidey3.domain.model.Comic

interface ComicDetailView {

    fun showComic(comic: Comic)

    fun updateImage(imageUrl: String, canShowPrevious: Boolean, canShowNext: Boolean)
}