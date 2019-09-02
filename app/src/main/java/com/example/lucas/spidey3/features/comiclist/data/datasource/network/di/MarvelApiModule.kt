package com.example.lucas.spidey3.features.comiclist.data.datasource.network.di

import com.example.lucas.spidey3.BuildConfig
import com.example.lucas.spidey3.features.comiclist.data.datasource.network.MarvelApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class MarvelApiModule {

    @Provides
    @Singleton
    internal fun provideMarvelApiClient(): Retrofit {
        val baseUrl = "https://gateway.marvel.com"

        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient().newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()

        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideMarvelApi(apiClient: Retrofit): MarvelApi {
        return apiClient.create(MarvelApi::class.java)
    }
}
