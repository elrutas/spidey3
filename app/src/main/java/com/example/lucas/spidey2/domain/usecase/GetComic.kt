package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.repository.ComicRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetComic @Inject constructor(private val comicRepository: ComicRepository) : Usecase<Comic> {

    var comicId : Int = -1

    fun withParams(comicId: Int) : GetComic {
        this.comicId = comicId
        return this
    }

    override fun getSubscribable(): Observable<Comic> {
        return comicRepository.getComic(comicId)
    }
}