package com.example.lucas.spidey2.di

import com.example.lucas.spidey2.ui.features.comiclist.di.ComicListComponent
import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getComicListComponent(): ComicListComponent
}
