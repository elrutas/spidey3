package com.example.lucas.spidey2.internal.di

import com.example.lucas.spidey2.ui.features.comiclist.di.ComicListComponent
import com.example.lucas.spidey2.ui.features.comiclist.di.ComicListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getComicListComponent(comicListModule: ComicListModule): ComicListComponent
}
