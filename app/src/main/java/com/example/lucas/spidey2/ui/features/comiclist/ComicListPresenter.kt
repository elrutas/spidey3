package com.example.lucas.spidey2.ui.features.comiclist

import com.example.lucas.spidey2.domain.model.SuperHero
import com.example.lucas.spidey2.domain.usecase.GetComicsForSuperHero
import com.example.lucas.spidey2.ui.features.BasePresenter
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicPM
import timber.log.Timber
import javax.inject.Inject

class ComicListPresenter @Inject constructor(val view: ComicListView,
                                             val getComicsForSuperHero: GetComicsForSuperHero)
    : BasePresenter() {

    var state = ComicListState()
    val presentationMapper = ComicListPresentationMapper()

    fun loadComics() {
        if (state.status == ComicListState.Status.LOADING) {
            return
        }

        state.status = ComicListState.Status.LOADING
        updateView()

        execute(getComicsForSuperHero.withParams(SuperHero.SPIDEY, 20, state.comics.size),
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
        view.launchDetailActivity(comic.id, comic.title)
    }

    private fun updateView() {
        view.showComics(presentationMapper.map(state))
    }

    override fun stop() {
        super.stop()
        state.status = ComicListState.Status.IDLE
    }

}