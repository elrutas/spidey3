package com.example.lucas.spidey2.data.remote.di

import com.example.lucas.spidey2.data.remote.MarvelApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MarvelApiModule {

    @Provides
    @Singleton
    internal fun provideMarvelApiClient(): Retrofit {
        val baseUrl = "http://gateway.marvel.com"

        val okHttpClient = OkHttpClient().newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()

        val gBuilder = GsonBuilder()
        val gson = gBuilder.create()

        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideMarvelApi(apiClient: Retrofit): MarvelApi {
        return apiClient.create(MarvelApi::class.java)
    }
}
