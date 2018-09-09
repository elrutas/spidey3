package com.example.lucas.spidey2.data.remote.datastore

import com.example.lucas.spidey2.data.remote.CreateHashUtil
import com.example.lucas.spidey2.data.remote.MarvelApi
import com.example.lucas.spidey2.data.remote.mapper.ComicMapper
import com.example.lucas.spidey2.domain.model.Comic
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ComicDataStore @Inject constructor(private val marvelApi: MarvelApi,
                                         @Named("ApiPrivateKey") private val privateApiKey: String,
                                         @Named("ApiPublicKey") private val publicApiKey: String) {

    val comicMapper: ComicMapper = ComicMapper()

    fun getListOfComics(superHeroId: String, amount: Int, offset: Int): Observable<List<Comic>> {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val timestamp = calendar.timeInMillis / 1000L
        val hash: String = CreateHashUtil.md5(timestamp.toString() + privateApiKey + publicApiKey)

        return marvelApi.getCharacterComicsData(superHeroId, timestamp.toString(), publicApiKey, hash, amount, offset)
                .map { comicMapper.map(it) }
    }
}