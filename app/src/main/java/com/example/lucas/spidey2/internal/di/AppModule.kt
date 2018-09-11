package com.example.lucas.spidey2.internal.di

import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.SpideyApp
import com.example.lucas.spidey2.data.remote.di.MarvelApiModule
import com.example.lucas.spidey2.data.remote.di.RepositoryModule
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
