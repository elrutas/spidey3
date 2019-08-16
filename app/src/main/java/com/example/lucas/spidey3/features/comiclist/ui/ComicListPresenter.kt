package com.example.lucas.spidey3.features.comiclist.ui

import android.widget.ImageView
import com.example.lucas.spidey3.features.comicdetail.domain.model.SuperHero
import com.example.lucas.spidey3.features.common.domain.usecase.GetComicsForSuperHero
import com.example.lucas.spidey3.features.common.ui.BasePresenter
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicPM
import timber.log.Timber
import javax.inject.Inject

class ComicListPresenter @Inject constructor(val view: ComicListView,
                                             val getComicsForSuperHero: GetComicsForSuperHero
)
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

    override fun stop() {
        super.stop()
        if (state.status == ComicListState.Status.LOADING) {
            state.status = ComicListState.Status.NEEDS_LOAD
        }
    }

    fun comicClicked(comic: ComicPM, comicThumbnail: ImageView) {
        view.launchDetailActivity(comic, comicThumbnail)
    }

    private fun updateView() {
        view.showComics(presentationMapper.map(state))
    }

    fun checkStatusAndLoadIfNeeded() {
        if (state.status == ComicListState.Status.NEEDS_LOAD) {
            loadComics()
        }
    }

}