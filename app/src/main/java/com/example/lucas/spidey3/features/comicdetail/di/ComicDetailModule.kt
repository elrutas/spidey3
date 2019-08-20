package com.example.lucas.spidey3.features.comicdetail.di

import androidx.lifecycle.ViewModel
import com.example.lucas.spidey3.features.comicdetail.ui.ComicDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
class ComicDetailModule {

    @Provides
    @IntoMap
    @ClassKey(ComicDetailViewModel::class)
    fun provideComicDetailViewModel(viewModel: ComicDetailViewModel): ViewModel {
        return viewModel
    }
}