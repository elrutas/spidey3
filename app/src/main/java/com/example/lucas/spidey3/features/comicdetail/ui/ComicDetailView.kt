package com.example.lucas.spidey3.features.comicdetail.ui

import com.example.lucas.spidey3.features.comicdetail.domain.model.Comic

interface ComicDetailView {

    fun showComic(comic: Comic)

    fun updateImage(imageUrl: String, canShowPrevious: Boolean, canShowNext: Boolean)
}