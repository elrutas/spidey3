package com.example.lucas.spidey3.features.comiclist.data.datasource.network

import com.example.lucas.spidey3.features.comiclist.data.datasource.network.converter.ComicConverter
import com.example.lucas.spidey3.features.comiclist.data.datasource.network.util.CreateHashUtil
import com.example.lucas.spidey3.features.comiclist.domain.model.Comic
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ComicDataStore @Inject constructor(private val marvelApi: MarvelApi,
                                         @Named("ApiPrivateKey") private val privateApiKey: String,
                                         @Named("ApiPublicKey") private val publicApiKey: String) {

    private val comicConverter: ComicConverter = ComicConverter()

    fun getListOfComics(superHeroId: String, amount: Int, offset: Int): Single<List<Comic>> {
        val (timestamp, hash) = getTimestampAndHash()

        return marvelApi.getCharacterComicsData(superHeroId, timestamp, publicApiKey, hash, amount, offset)
                .map { comicConverter.map(it) }
    }

    fun getComic(comicId: Int): Single<Comic> {
        val (timestamp, hash) = getTimestampAndHash()

        return marvelApi.getComic(comicId, timestamp, publicApiKey, hash)
                .map { comicConverter.map(it) }
                .map { it[0] }
    }

    private fun getTimestampAndHash(): Pair<String, String> {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val timestamp = calendar.timeInMillis / 1000L
        val hash: String =
            CreateHashUtil.md5(
                timestamp.toString() + privateApiKey + publicApiKey
            )

        return Pair<String, String>(timestamp.toString(), hash)
    }
}