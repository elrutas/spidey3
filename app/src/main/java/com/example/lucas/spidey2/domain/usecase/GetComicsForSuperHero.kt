package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.data.remote.entity.ComicDataWrapper
import com.example.lucas.spidey2.domain.repository.ComicRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetComicsForSuperHero @Inject constructor(private val comicRepository: ComicRepository) {

    fun get(): Observable<ComicDataWrapper> {
        return comicRepository.getListOfComics(ComicRepository.SuperHero.SPIDEY, 50, 0)
    }
}