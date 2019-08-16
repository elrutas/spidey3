package com.example.lucas.spidey3.data.repository.di

import com.example.lucas.spidey3.data.remote.datastore.ComicDataStore
import com.example.lucas.spidey3.data.repository.ComicRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideComicRepository(comicDataStore: ComicDataStore): ComicRepository {
        return ComicRepository(comicDataStore)
    }
}
