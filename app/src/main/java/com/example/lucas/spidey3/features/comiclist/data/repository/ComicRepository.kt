package com.example.lucas.spidey3.features.comiclist.data.repository

import com.example.lucas.spidey3.features.comiclist.data.network.datastore.ComicDataStore
import com.example.lucas.spidey3.features.comiclist.domain.model.Comic
import com.example.lucas.spidey3.features.comiclist.domain.model.SuperHero
import io.reactivex.Single

class ComicRepository(val remote: ComicDataStore) {

    fun getListOfComics(superHero: SuperHero, amount: Int, offset: Int): Single<List<Comic>> {
        return remote.getListOfComics(superHero.id, amount, offset)
    }

    fun getComic(comicId: Int): Single<Comic> {
        return remote.getComic(comicId)
    }
}