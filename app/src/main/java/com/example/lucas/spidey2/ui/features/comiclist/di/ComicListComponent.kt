package com.example.lucas.spidey2.ui.features.comiclist.di

import com.example.lucas.spidey2.ui.features.comiclist.ComicListActivity
import dagger.Subcomponent

@Subcomponent
interface ComicListComponent {

    fun inject(comicListActivity: ComicListActivity)
}