package com.example.lucas.spidey2.ui.features.comicdetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.internal.di.Injector
import com.example.lucas.spidey2.internal.extensions.loadUrl
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
        setupBottomSheet()
    }

    private fun getIntentExtra() {
        val comicId = intent.getIntExtra(COMIC_ID_EXTRA, -1)
        val comicTitle = intent.getStringExtra(COMIC_TITLE_EXTRA)
        val comicThumbnailUrl = intent.getStringExtra(COMIC_THUMBNAIL)

        if (comicId  == -1 || comicThumbnailUrl == null || comicTitle == null) {
            finish()  // TODO: more "gentle" error handling
            return
        }

        comic_detail_title.text = comicTitle
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

    private fun setupBottomSheet() {
        comic_detail_parent_layout.setOnClickListener { _ ->
            val bottomSheetBehaviour = BottomSheetBehavior.from(comic_detail_bottom_sheet)
            if (bottomSheetBehaviour.state == BottomSheetBehavior.STATE_HIDDEN) {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            } else if (bottomSheetBehaviour.state == BottomSheetBehavior.STATE_EXPANDED
                    || bottomSheetBehaviour.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
    }

    override fun showComic(comic: Comic) {
        comic_detail_description.text = comic.description

        if (comic.imageUrls.size > 1) {
            setupImageNavigator()
        }
    }

    override fun updateImage(imageUrl: String, canShowPrevious: Boolean, canShowNext: Boolean) {
        comic_detail_image.loadUrl(imageUrl)
        comic_detail_previous_icon.visibility = if (canShowPrevious) View.VISIBLE else View.INVISIBLE
        comic_detail_next_icon.visibility = if (canShowNext) View.VISIBLE else View.INVISIBLE
    }

    private fun setupImageNavigator() {
        comic_detail_next_icon.setOnClickListener { _ -> presenter.showNextImage() }
        comic_detail_previous_icon.setOnClickListener { _ -> presenter.showPreviousImage() }
        comic_detail_next_icon.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}
