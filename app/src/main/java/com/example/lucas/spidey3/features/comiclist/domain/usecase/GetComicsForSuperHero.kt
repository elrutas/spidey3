package com.example.lucas.spidey3.features.comiclist.domain.usecase

import com.example.lucas.spidey3.features.common.data.repository.ComicRepository
import com.example.lucas.spidey3.features.common.domain.model.Comic
import com.example.lucas.spidey3.features.comiclist.domain.model.SuperHero
import com.example.lucas.spidey3.features.common.domain.usecase.Usecase
import io.reactivex.Single
import javax.inject.Inject
import kotlin.properties.Delegates


class GetComicsForSuperHero @Inject constructor(private val comicRepository: ComicRepository)
    : Usecase<List<Comic>> {

    lateinit var superHero: SuperHero
    var amount: Int by Delegates.notNull()
    var offset: Int by Delegates.notNull()

    fun withParams(superHero: SuperHero, amount: Int, offset: Int): GetComicsForSuperHero {
        this.superHero = superHero
        this.amount = amount
        this.offset = offset

        return this
    }

    override fun getSubscribable(): Single<List<Comic>> {
        return comicRepository.getListOfComics(superHero, amount, offset)
    }
}