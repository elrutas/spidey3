package com.example.lucas.spidey2.domain.repository

import com.example.lucas.spidey2.data.remote.datastore.ComicDataStore
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.model.SuperHero
import io.reactivex.Observable

class ComicRepository(val remote: ComicDataStore) {

    fun getListOfComics(superHero: SuperHero, amount: Int, offset: Int): Observable<List<Comic>> {
        return remote.getListOfComics(superHero.id, amount, offset)
    }

    fun getComic(comicId: Int): Observable<Comic> {
        return remote.getComic(comicId)
                .map { it.get(0) }
    }
}