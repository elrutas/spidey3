package com.example.lucas.spidey3.features.comiclist.di

import com.example.lucas.spidey3.features.comiclist.ui.ComicListActivity
import dagger.Subcomponent

@ComicListScope
@Subcomponent(modules = [ComicListModule::class])
interface ComicListComponent {

    fun inject(comicListActivity: ComicListActivity)
}