package com.example.lucas.spidey2.ui.features.comiclist

import com.example.lucas.spidey2.domain.model.SuperHero
import com.example.lucas.spidey2.domain.usecase.GetComicsForSuperHero
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicPM
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ComicListPresenter @Inject constructor(val view: ComicListView,
                                             val getComicsForSuperHero: GetComicsForSuperHero) {

    var state = ComicListState()
    val presentationMapper = ComicListPresentationMapper()

    fun loadComics() {
        if (state.status == ComicListState.Status.LOADING) {
            return
        }

        state.status = ComicListState.Status.LOADING
        updateView()

        getComicsForSuperHero.withParams(SuperHero.SPIDEY, 20, state.comics.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { comics ->
                            state.status = ComicListState.Status.IDLE
                            state.comics.addAll(comics)
                            updateView()
                        },
                        { throwable ->
                            state.status = ComicListState.Status.ERROR
                            Timber.e(throwable)
                            updateView()
                        }
                )
    }

    fun comicClicked(comic: ComicPM) {
        Timber.d("Comic clicked ${comic.title}")
    }

    private fun updateView() {
        view.showComics(presentationMapper.map(state))
    }

}