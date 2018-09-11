package com.example.lucas.spidey2.ui.features.comicdetail

import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.usecase.GetComic
import com.example.lucas.spidey2.ui.features.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class ComicDetailPresenter @Inject constructor(val view: ComicDetailView,
                                               val getComic: GetComic)
    : BasePresenter() {

    fun getComic(comicId: Int) {
        execute(getComic.withParams(comicId),
                { comic -> udpateView(comic) },
                { throwable -> Timber.e(throwable) }
        )
    }

    private fun udpateView(comic: Comic) {
        view.showComic(comic)
    }
}

