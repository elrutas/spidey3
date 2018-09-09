package com.example.lucas.spidey2.domain.repository

import com.example.lucas.spidey2.data.remote.datastore.ComicDataStore
import com.example.lucas.spidey2.data.remote.entity.ComicDataWrapperEntity
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.model.SuperHero
import io.reactivex.Observable
import javax.inject.Inject

class ComicRepository @Inject constructor(val remote: ComicDataStore) {

    fun getListOfComics(superHero: SuperHero, amount: Int, offset: Int): Observable<List<Comic>> {
        return remote.getListOfComics(superHero.id, amount, offset)
    }
}