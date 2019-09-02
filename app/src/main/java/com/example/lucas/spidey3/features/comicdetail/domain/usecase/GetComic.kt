package com.example.lucas.spidey3.features.comicdetail.domain.usecase

import com.example.lucas.spidey3.features.comiclist.data.repository.ComicRepository
import com.example.lucas.spidey3.features.common.domain.usecase.Usecase
import com.example.lucas.spidey3.features.comiclist.domain.model.Comic
import io.reactivex.Single
import javax.inject.Inject
import kotlin.properties.Delegates

class GetComic @Inject constructor(private val comicRepository: ComicRepository) : Usecase<Comic> {

    var comicId :Int by Delegates.notNull()

    fun withParams(comicId: Int) : GetComic {
        this.comicId = comicId
        return this
    }

    override fun getSubscribable(): Single<Comic> {
        return comicRepository.getComic(comicId)
    }
}