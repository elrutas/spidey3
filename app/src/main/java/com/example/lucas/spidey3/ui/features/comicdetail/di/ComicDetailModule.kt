package com.example.lucas.spidey3.ui.features.comicdetail.di

import com.example.lucas.spidey3.ui.features.comicdetail.ComicDetailView
import dagger.Module
import dagger.Provides

@Module
class ComicDetailModule(val view : ComicDetailView) {

    @Provides
    @ComicDetailScope
    fun provideComicDetailView(): ComicDetailView {
        return view
    }
}