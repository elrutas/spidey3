package com.example.lucas.spidey2.data.remote.di

import com.example.lucas.spidey2.data.remote.datastore.ComicDataStore
import com.example.lucas.spidey2.domain.repository.ComicRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideMarvelApiClient(comicDataStore: ComicDataStore): ComicRepository {
        return ComicRepository(comicDataStore)
    }
}
