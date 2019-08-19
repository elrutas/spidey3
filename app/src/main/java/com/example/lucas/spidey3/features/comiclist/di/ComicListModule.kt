package com.example.lucas.spidey3.features.comiclist.di

import androidx.lifecycle.ViewModel
import com.example.lucas.spidey3.features.comiclist.ui.ComicListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
class ComicListModule {

    @Provides
    @IntoMap
    @ClassKey(ComicListViewModel::class)
    fun provideComicListViewModel(viewModel: ComicListViewModel): ViewModel {
        return viewModel
    }
}