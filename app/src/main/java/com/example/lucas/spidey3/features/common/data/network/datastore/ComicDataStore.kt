package com.example.lucas.spidey3.features.common.data.network.datastore

import com.example.lucas.spidey3.features.common.data.network.MarvelApi
import com.example.lucas.spidey3.features.common.data.network.mapper.ComicMapper
import com.example.lucas.spidey3.features.comicdetail.domain.model.Comic
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ComicDataStore @Inject constructor(private val marvelApi: MarvelApi,
                                         @Named("ApiPrivateKey") private val privateApiKey: String,
                                         @Named("ApiPublicKey") private val publicApiKey: String) {

    val comicMapper: ComicMapper = ComicMapper()

    fun getListOfComics(superHeroId: String, amount: Int, offset: Int): Single<List<Comic>> {
        val (timestamp, hash) = getTimestampAndHash()

        return marvelApi.getCharacterComicsData(superHeroId, timestamp, publicApiKey, hash, amount, offset)
                .map { comicMapper.map(it) }
    }

    fun getComic(comicId: Int): Single<List<Comic>> {
        val (timestamp, hash) = getTimestampAndHash()

        return marvelApi.getComic(comicId, timestamp, publicApiKey, hash)
                .map { comicMapper.map(it) }
    }

    fun getTimestampAndHash(): Pair<String, String> {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val timestamp = calendar.timeInMillis / 1000L
        val hash: String = CreateHashUtil.md5(timestamp.toString() + privateApiKey + publicApiKey)

        return Pair<String, String>(timestamp.toString(), hash)
    }
}