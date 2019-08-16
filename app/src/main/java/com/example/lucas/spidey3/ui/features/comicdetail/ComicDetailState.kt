package com.example.lucas.spidey3.ui.features.comicdetail

import com.example.lucas.spidey3.domain.model.Comic

class ComicDetailState {

    lateinit var comic: Comic
    var currentImageIndex = 0

    fun getCurrentImageUrl(): String {
        return comic.imageUrls[currentImageIndex]
    }

    fun canShowPrevious(): Boolean {
        return currentImageIndex > 0
    }

    fun canShowNext(): Boolean {
        return currentImageIndex < comic.imageUrls.size - 1
    }
}
