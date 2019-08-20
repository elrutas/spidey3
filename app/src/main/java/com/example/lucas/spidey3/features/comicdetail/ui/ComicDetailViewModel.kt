package com.example.lucas.spidey3.features.comicdetail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lucas.spidey3.features.comicdetail.domain.usecase.GetComic
import com.example.lucas.spidey3.features.common.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class ComicDetailViewModel @Inject constructor(
    val getComic: GetComic
) : BaseViewModel() {

    private val liveData: MutableLiveData<ComicDetailState> = MutableLiveData()

    fun getComic(comicId: Int) {
        execute(getComic.withParams(comicId),
                { comic -> liveData.value = ComicDetailState(comic) },
                { throwable -> Timber.e(throwable) }
        )
    }

    fun comicState() : LiveData<ComicDetailState> {
        return liveData
    }

    fun showNextImage() {
        val comicDetailState = liveData.value ?: return

        comicDetailState.showNextImage()

        liveData.value = comicDetailState
    }

    fun showPreviousImage() {
        val comicDetailState = liveData.value ?: return

        comicDetailState.showPreviousImage()

        liveData.value = comicDetailState
    }
}

