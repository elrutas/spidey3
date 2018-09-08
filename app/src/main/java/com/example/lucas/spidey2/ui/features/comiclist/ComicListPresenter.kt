package com.example.lucas.spidey2.ui.features.comiclist

import android.util.Log
import com.example.lucas.spidey2.domain.usecase.GetComicsForSuperHero
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ComicListPresenter @Inject constructor(val getComicsForSuperHero: GetComicsForSuperHero) {

    fun getComics() {
        getComicsForSuperHero.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { comics -> Log.d("luk", comics.status) },
                        { throwable ->  Log.e("luk", throwable?.message)  }
                )
    }

}