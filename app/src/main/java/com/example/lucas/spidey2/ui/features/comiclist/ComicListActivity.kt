package com.example.lucas.spidey2.ui.features.comiclist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.SpideyApp
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.ui.features.comiclist.adapter.ComicListAdapter
import com.example.lucas.spidey2.ui.features.comiclist.adapter.EndlessRecyclerViewScrollListener
import com.example.lucas.spidey2.ui.features.comiclist.di.ComicListModule
import kotlinx.android.synthetic.main.activity_comic_list.*
import javax.inject.Inject

class ComicListActivity : AppCompatActivity(), ComicListView {

    @Inject lateinit var presenter: ComicListPresenter

    lateinit var comicListAdapter: ComicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_list)

        (applicationContext as SpideyApp).component
                .getComicListComponent(ComicListModule(this))
                .inject(this)

        setupComicList()
    }

    override fun onResume() {
        super.onResume()
        presenter.getComics()
    }

    private fun setupComicList() {
        comicListAdapter = ComicListAdapter { presenter.comicClicked(it) }

        comic_list_grid.setHasFixedSize(true)
        comic_list_grid.adapter = comicListAdapter
        comic_list_grid.addOnScrollListener(object : EndlessRecyclerViewScrollListener(comic_list_grid.manager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                presenter.getComics()
            }
        })
    }

    override fun showComics(comics: List<Comic>) {
        comicListAdapter.addComics(comics)
    }
}
