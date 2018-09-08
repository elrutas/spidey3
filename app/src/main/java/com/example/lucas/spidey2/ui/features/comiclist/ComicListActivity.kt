package com.example.lucas.spidey2.ui.features.comiclist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.SpideyApp
import javax.inject.Inject

class ComicListActivity : AppCompatActivity() {

    @Inject lateinit var presenter: ComicListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_list)

        (applicationContext as SpideyApp).component
                .getComicListComponent()
                .inject(this)
        getComics()
    }

    private fun getComics() {
        presenter.getComics()
    }
}
