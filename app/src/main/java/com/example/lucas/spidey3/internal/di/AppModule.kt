package com.example.lucas.spidey3.internal.di

import com.example.lucas.spidey3.R
import com.example.lucas.spidey3.SpideyApp
import com.example.lucas.spidey3.data.remote.di.MarvelApiModule
import com.example.lucas.spidey3.data.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [MarvelApiModule::class, RepositoryModule::class])
class AppModule(val spideyApp: SpideyApp) {

    @Provides
    @Singleton
    @Named("ApiPrivateKey")
    fun provideApiPrivateKey(): String {
        return spideyApp.getString(R.string.marvel_api_private_key)
    }

    @Provides
    @Singleton
    @Named("ApiPublicKey")
    fun provideApiPublicKey(): String {
        return spideyApp.getString(R.string.marvel_api_public_key)
    }
}
