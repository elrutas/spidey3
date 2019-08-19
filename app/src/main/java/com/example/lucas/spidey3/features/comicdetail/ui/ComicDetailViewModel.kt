package com.example.lucas.spidey3.features.comicdetail.ui

import com.example.lucas.spidey3.features.comicdetail.domain.usecase.GetComic
import com.example.lucas.spidey3.features.common.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class ComicDetailViewModel @Inject constructor(
    val view: ComicDetailView,
    val getComic: GetComic
) : BaseViewModel() {

    var state: ComicDetailState = ComicDetailState()

    fun getComic(comicId: Int) {
        execute(getComic.withParams(comicId),
                { comic ->
                    state.comic = comic
                    udpateView()
                },
                { throwable -> Timber.e(throwable) }
        )
    }

    private fun udpateView() {
        view.showComic(state.comic)
    }

    fun showNextImage() {
        if (state.canShowNext()) {
            state.currentImageIndex++
            updateDisplayedImage()
        }
    }

    fun showPreviousImage() {
        if (state.canShowPrevious()) {
            state.currentImageIndex--
            updateDisplayedImage()
        }
    }

    private fun updateDisplayedImage() {
        view.updateImage(state.getCurrentImageUrl(), state.canShowPrevious(), state.canShowNext())
    }
}

