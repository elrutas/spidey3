package com.example.lucas.spidey3.features.comicdetail.ui

import com.example.lucas.spidey3.features.comiclist.domain.model.Comic

class ComicDetailState(val comic: Comic) {

    var currentImageIndex = 0

    fun moreThanOneImage(): Boolean {
        return comic.imageUrls.size > 1
    }

    fun getCurrentImageUrl(): String {
        return comic.imageUrls[currentImageIndex]
    }

    fun canShowPrevious(): Boolean {
        return currentImageIndex > 0
    }

    fun canShowNext(): Boolean {
        return currentImageIndex < comic.imageUrls.size - 1
    }

    fun showNextImage() {
        if (canShowNext()) {
            currentImageIndex++
        }
    }

    fun showPreviousImage() {
        if (canShowPrevious()) {
            currentImageIndex--
        }
    }
}
