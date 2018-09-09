package com.example.lucas.spidey2.ui.features.comiclist

import android.util.Log
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.usecase.GetComicsForSuperHero
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
                        { throwable ->  Log.e("luk", throwable?.message)  }
                )
    }

    fun comicClicked(comic: Comic) {
        Log.d("luk", "Comic clicked ${comic.title}")
    }

}