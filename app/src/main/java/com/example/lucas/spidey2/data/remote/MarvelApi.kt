package com.example.lucas.spidey2.data.remote

import com.example.lucas.spidey2.data.remote.entity.ComicDataWrapperEntity

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters/{characterId}/comics")
    fun getCharacterComicsData(
            @Path("characterId") characterId: String,
            @Query("ts") timestamp: String,
            @Query("apikey") api_key: String,
            @Query("hash") md5Hash: String,
            @Query("limit") limit: Int,
            @Query("offset") offset: Int): Observable<ComicDataWrapperEntity>
}
