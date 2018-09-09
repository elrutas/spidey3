package com.example.lucas.spidey2.ui.features.comiclist

import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.usecase.GetComicsForSuperHero
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ComicListPresenter @Inject constructor(val getComicsForSuperHero: GetComicsForSuperHero) {

    lateinit var view: ComicListView

    fun attachView(comicListView: ComicListView) {
        view = comicListView
    }

    fun getComics() {
        getComicsForSuperHero.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { comics -> view.addComicsToList(comics) },
                        { throwable ->  Timber.e(throwable)  }
                )
    }

    fun comicClicked(comic: Comic) {
        Timber.d("Comic clicked ${comic.title}")
    }

}