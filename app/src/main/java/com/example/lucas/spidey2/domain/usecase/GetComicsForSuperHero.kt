package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.model.SuperHero
import com.example.lucas.spidey2.domain.repository.ComicRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetComicsForSuperHero @Inject constructor(private val comicRepository: ComicRepository) {

    fun withParams(superHero: SuperHero, amount: Int, offset: Int): Observable<List<Comic>> {
        return comicRepository.getListOfComics(superHero, amount, offset)
    }
}