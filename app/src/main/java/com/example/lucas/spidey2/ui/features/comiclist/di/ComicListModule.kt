package com.example.lucas.spidey2.ui.features.comiclist.di

import com.example.lucas.spidey2.ui.features.comiclist.ComicListView
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