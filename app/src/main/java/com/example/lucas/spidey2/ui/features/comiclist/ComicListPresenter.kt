package com.example.lucas.spidey2.ui.features.comiclist

import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.model.SuperHero
import com.example.lucas.spidey2.domain.usecase.GetComicsForSuperHero
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ComicListPresenter @Inject constructor(val view: ComicListView,
                                             val getComicsForSuperHero: GetComicsForSuperHero) {

    var state = ComicListState()

    fun getComics() {
        getComicsForSuperHero.withParams(SuperHero.SPIDEY, 20, state.comics.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { comics ->
                            state.comics.addAll(comics)
                            updateView()
                        },
                        { throwable ->  Timber.e(throwable)  }
                )
    }

    fun comicClicked(comic: Comic) {
        Timber.d("Comic clicked ${comic.title}")
    }

    private fun updateView() {
        view.showComics(state.comics)
    }

}