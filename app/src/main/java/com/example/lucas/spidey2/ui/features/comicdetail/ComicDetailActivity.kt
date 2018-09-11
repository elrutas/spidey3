package com.example.lucas.spidey2.ui.features.comicdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.internal.di.Injector
import com.example.lucas.spidey2.ui.features.comicdetail.di.ComicDetailModule

class ComicDetailActivity : AppCompatActivity(), ComicDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_detail_activity)

        Injector.getAppComponent()
                .getComicDetailComponent(ComicDetailModule(this))
                .inject(this)
    }

    companion object {
        const val COMIC_ID_EXTRA = "comicId"
    }
}
