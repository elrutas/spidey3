package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.data.remote.entity.ComicDataWrapperEntity
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.model.SuperHero
import com.example.lucas.spidey2.domain.repository.ComicRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetComicsForSuperHero @Inject constructor(private val comicRepository: ComicRepository) {

    fun get(): Observable<List<Comic>> {
        return comicRepository.getListOfComics(SuperHero.SPIDEY, 50, 0)
    }
}