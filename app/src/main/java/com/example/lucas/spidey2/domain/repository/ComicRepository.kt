package com.example.lucas.spidey2.domain.repository

import com.example.lucas.spidey2.data.remote.datastore.ComicDataStore
import com.example.lucas.spidey2.data.remote.entity.ComicDataWrapper
import io.reactivex.Observable
import javax.inject.Inject

class ComicRepository @Inject constructor(val remote: ComicDataStore) {

    enum class SuperHero(val id: String) {
        SPIDEY("1009610"),
        CAPTAIN_AMERICA("1009220")
    }

    fun getListOfComics(superHero: SuperHero, amount: Int, offset: Int): Observable<ComicDataWrapper> {
        return remote.getListOfComics(superHero.id, amount, offset)
    }
}