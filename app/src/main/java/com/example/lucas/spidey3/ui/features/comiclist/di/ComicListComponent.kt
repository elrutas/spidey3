package com.example.lucas.spidey3.ui.features.comiclist.di

import com.example.lucas.spidey3.ui.features.comiclist.ComicListActivity
import dagger.Subcomponent

@ComicListScope
@Subcomponent(modules = [ComicListModule::class])
interface ComicListComponent {

    fun inject(comicListActivity: ComicListActivity)
}