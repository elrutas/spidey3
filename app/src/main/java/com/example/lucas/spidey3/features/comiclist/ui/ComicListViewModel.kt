package com.example.lucas.spidey3.features.comiclist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lucas.spidey3.features.comicdetail.domain.model.SuperHero
import com.example.lucas.spidey3.features.common.domain.usecase.GetComicsForSuperHero
import com.example.lucas.spidey3.features.common.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class ComicListViewModel @Inject constructor(
    private val getComicsForSuperHero: GetComicsForSuperHero
): BaseViewModel() {

    private val liveData: MutableLiveData<ComicListState> = MutableLiveData()

    var state = ComicListState()

    fun loadComics() {
        if (state.status == ComicListState.Status.LOADING) {
            return
        }

        state.status = ComicListState.Status.LOADING
        updateState()

        execute(getComicsForSuperHero.withParams(SuperHero.SPIDEY, 20, state.comics.size),
                { comics ->
                    state.status = ComicListState.Status.IDLE
                    state.comics.addAll(comics)
                    updateState()
                },
                { throwable ->
                    state.status = ComicListState.Status.ERROR
                    Timber.e(throwable)
                    updateState()
                }
        )
    }

    fun liveState() : LiveData<ComicListState> {
        return liveData
    }

    private fun updateState() {
        liveData.value = state
    }
}