package com.example.lucas.spidey3.internal.di

import com.example.lucas.spidey3.features.comicdetail.di.ComicDetailModule
import com.example.lucas.spidey3.features.comiclist.di.ComicListModule
import com.example.lucas.spidey3.features.common.ui.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ComicListModule::class, ComicDetailModule::class])
interface AppComponent {

    fun inject(baseActivity: BaseActivity)
}
