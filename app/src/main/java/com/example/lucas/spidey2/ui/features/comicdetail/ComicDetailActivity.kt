package com.example.lucas.spidey2.ui.features.comicdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.internal.di.Injector
import com.example.lucas.spidey2.internal.extensions.loadUrl
import com.example.lucas.spidey2.internal.extensions.random
import com.example.lucas.spidey2.ui.features.comicdetail.di.ComicDetailModule
import kotlinx.android.synthetic.main.comic_detail_activity.*
import javax.inject.Inject

class ComicDetailActivity : AppCompatActivity(), ComicDetailView {

    companion object {
        const val COMIC_ID_EXTRA = "comicId"
        const val COMIC_TITLE_EXTRA = "comicTitle"
    }

    @Inject lateinit var presenter: ComicDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_detail_activity)

        Injector.getAppComponent()
                .getComicDetailComponent(ComicDetailModule(this))
                .inject(this)

        getIntentExtra()
    }

    private fun getIntentExtra() {
        val comicId = intent.getIntExtra(COMIC_ID_EXTRA, -1)
        val comicTitle = intent.getStringExtra(COMIC_TITLE_EXTRA)

        if (comicId  == -1) {
            finish()  // TODO: more "gentle" error handling
        }

        title = comicTitle
        presenter.getComic(comicId)
    }

    override fun showComic(comic: Comic) {
        comic_detail_title.text = comic.title
        comic_detail_description.text = comic.description

        if (comic.imageUrls.isNotEmpty()) {
            comic_detail_image.loadUrl(comic.imageUrls.random())
        } else {
            comic_detail_image.loadUrl(comic.thumbnailUrl)
        }
    }
}
