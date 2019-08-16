package com.example.lucas.spidey3.features.comicdetail.di

import com.example.lucas.spidey3.features.comicdetail.ui.ComicDetailView
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