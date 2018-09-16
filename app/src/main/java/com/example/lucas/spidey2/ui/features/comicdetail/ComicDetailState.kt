package com.example.lucas.spidey2.ui.features.comicdetail

import com.example.lucas.spidey2.domain.model.Comic

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
