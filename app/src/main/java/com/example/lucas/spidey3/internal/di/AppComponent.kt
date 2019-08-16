package com.example.lucas.spidey3.internal.di

import com.example.lucas.spidey3.features.comicdetail.di.ComicDetailComponent
import com.example.lucas.spidey3.features.comicdetail.di.ComicDetailModule
import com.example.lucas.spidey3.features.comiclist.di.ComicListComponent
import com.example.lucas.spidey3.features.comiclist.di.ComicListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getComicListComponent(comicListModule: ComicListModule): ComicListComponent

    fun getComicDetailComponent(comicDetailModule: ComicDetailModule): ComicDetailComponent
}
