package com.example.lucas.spidey2.ui.features.comicdetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.internal.di.Injector
import com.example.lucas.spidey2.internal.utils.GlideApp
import com.example.lucas.spidey2.ui.features.comicdetail.di.ComicDetailModule
import kotlinx.android.synthetic.main.comic_detail_activity.*
import javax.inject.Inject

class ComicDetailActivity : AppCompatActivity(), ComicDetailView {

    companion object {
        const val COMIC_ID_EXTRA = "comicId"
        const val COMIC_TITLE_EXTRA = "comicTitle"
        const val COMIC_THUMBNAIL = "comicThumbnailUrl"
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
        val comicThumbnailUrl = intent.getStringExtra(COMIC_THUMBNAIL)

        if (comicId  == -1 || comicThumbnailUrl == null) {
            finish()  // TODO: more "gentle" error handling
            return
        }

        title = comicTitle
        loadThumbnail(comicThumbnailUrl)
        presenter.getComic(comicId)
    }

    private fun loadThumbnail(thumbnailUrl: String) {

        GlideApp.with(this)
                .load(thumbnailUrl)
                .fitCenter()
                .dontAnimate()
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        postponeEnterTransition()
                        return false;
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        postponeEnterTransition()
                        return false;
                    }

                })
                .into(comic_detail_image)
    }

    override fun showComic(comic: Comic) {
        comic_detail_title.text = comic.title
        comic_detail_description.text = comic.description

    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}
