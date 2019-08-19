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

    fun loadComics() {
        if (liveData.value?.loading == true) {
            return
        }

        val state = liveData.value?.let { ComicListState.Loading(it) } ?: ComicListState.Init()
        liveData.value = state

        execute(getComicsForSuperHero.withParams(SuperHero.SPIDEY, 20, state.comics.size),
                { loadedComics -> liveData.value = ComicListState.Success(state, loadedComics) },
                { throwable ->
                    Timber.e(throwable)
                    liveData.value = ComicListState.Error(state)
                }
        )
    }

    fun liveState() : LiveData<ComicListState> {
        return liveData
    }
}