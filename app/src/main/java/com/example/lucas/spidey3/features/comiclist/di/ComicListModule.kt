package com.example.lucas.spidey3.features.comiclist.di

import com.example.lucas.spidey3.features.comiclist.ui.ComicListView
import dagger.Module
import dagger.Provides

@Module
class ComicListModule(val view : ComicListView) {

    @Provides
    @ComicListScope
    fun provideComicListView(): ComicListView {
        return view
    }
}