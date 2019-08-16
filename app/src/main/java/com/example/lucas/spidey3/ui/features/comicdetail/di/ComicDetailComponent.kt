package com.example.lucas.spidey3.ui.features.comicdetail.di

import com.example.lucas.spidey3.ui.features.comicdetail.ComicDetailActivity
import dagger.Subcomponent

@ComicDetailScope
@Subcomponent(modules = [ComicDetailModule::class])
interface ComicDetailComponent {

    fun inject(comicDetailActivity: ComicDetailActivity)
}