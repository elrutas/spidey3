package com.example.lucas.spidey2.ui.features.comicdetail

import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.usecase.GetComic
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ComicDetailPresenter @Inject constructor(val view: ComicDetailView,
                                               val getComic: GetComic) {

    fun getComic(comicId: Int) {
        getComic.withParams(comicId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { comic -> udpateView(comic)},
                        { throwable -> Timber.e(throwable) }
                )
    }

    private fun udpateView(comic: Comic) {
        view.showComic(comic)
    }
}

