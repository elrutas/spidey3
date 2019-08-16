package com.example.lucas.spidey3.features.common.data.repository

import com.example.lucas.spidey3.features.common.data.network.datastore.ComicDataStore
import com.example.lucas.spidey3.features.comicdetail.domain.model.Comic
import com.example.lucas.spidey3.features.comicdetail.domain.model.SuperHero
import io.reactivex.Single

class ComicRepository(val remote: ComicDataStore) {

    fun getListOfComics(superHero: SuperHero, amount: Int, offset: Int): Single<List<Comic>> {
        return remote.getListOfComics(superHero.id, amount, offset)
    }

    fun getComic(comicId: Int): Single<Comic> {
        return remote.getComic(comicId)
                .map { it.get(0) }
    }
}