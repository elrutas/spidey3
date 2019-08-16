package com.example.lucas.spidey3.features.comicdetail.di

import com.example.lucas.spidey3.features.comicdetail.ui.ComicDetailActivity
import dagger.Subcomponent

@ComicDetailScope
@Subcomponent(modules = [ComicDetailModule::class])
interface ComicDetailComponent {

    fun inject(comicDetailActivity: ComicDetailActivity)
}