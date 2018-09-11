package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.data.repository.ComicRepository
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.model.SuperHero
import io.reactivex.Observable
import javax.inject.Inject


class GetComicsForSuperHero @Inject constructor(private val comicRepository: ComicRepository)
    : Usecase<List<Comic>> {

    lateinit var superHero: SuperHero
    var amount: Int = -1
    var offset: Int = -1

    fun withParams(superHero: SuperHero, amount: Int, offset: Int): GetComicsForSuperHero {
        this.superHero = superHero
        this.amount = amount
        this.offset = offset

        return this
    }

    override fun getSubscribable(): Observable<List<Comic>> {
        return comicRepository.getListOfComics(superHero, amount, offset)
    }
}