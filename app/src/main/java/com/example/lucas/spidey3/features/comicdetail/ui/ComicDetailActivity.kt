package com.example.lucas.spidey3.features.comicdetail.ui

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import androidx.appcompat.app.AppCompatActivity
import android.view.GestureDetector
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.lucas.spidey3.R
import com.example.lucas.spidey3.features.comicdetail.domain.model.Comic
import com.example.lucas.spidey3.internal.di.Injector
import com.example.lucas.spidey3.internal.extensions.loadUrl
import com.example.lucas.spidey3.internal.utils.GlideApp
import com.example.lucas.spidey3.features.comicdetail.di.ComicDetailModule
import kotlinx.android.synthetic.main.comic_detail_activity.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ComicDetailActivity : AppCompatActivity(), ComicDetailView {

    companion object {
        const val COMIC_ID_EXTRA = "comicId"
        const val COMIC_TITLE_EXTRA = "comicTitle"
        const val COMIC_THUMBNAIL = "comicThumbnailUrl"
    }

    @Inject lateinit var viewModel: ComicDetailViewModel

    var translationAmount: Float by Delegates.notNull()
    lateinit var bottomSheetBehaviour: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_detail_activity)

        Injector.getAppComponent()
                .getComicDetailComponent(ComicDetailModule(this))
                .inject(this)

        getIntentExtra()
        setupResourcesForOverlayAnimation()
    }

    private fun setupResourcesForOverlayAnimation() {
        val margin = resources.getDimension(R.dimen.activity_horizontal_margin)
        val imageViewWidth = resources.getDimension(R.dimen.comic_detail_navigation_image_size)

        translationAmount = imageViewWidth + margin
        bottomSheetBehaviour = BottomSheetBehavior.from(comic_detail_bottom_sheet)

        comic_detail_parent_layout.setOnClickListener { _ -> changeOverlayViewsVisibility() }
    }

    private fun getIntentExtra() {
        val comicId = intent.getIntExtra(COMIC_ID_EXTRA, -1)
        val comicTitle = intent.getStringExtra(COMIC_TITLE_EXTRA)
        val comicThumbnailUrl = intent.getStringExtra(COMIC_THUMBNAIL)

        if (comicId == -1 || comicThumbnailUrl == null || comicTitle == null) {
            finish()  // TODO: more "gentle" error handling
            return
        }

        comic_detail_title.text = comicTitle
        loadThumbnail(comicThumbnailUrl)
        viewModel.getComic(comicId)
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

    private fun changeOverlayViewsVisibility() {
        if (bottomSheetBehaviour.state == BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            moveOutToRightAnimation(comic_detail_next_icon).reverse()
            moveOutToLeftAnimation(comic_detail_previous_icon).reverse()
        } else if (bottomSheetBehaviour.state == BottomSheetBehavior.STATE_EXPANDED
                || bottomSheetBehaviour.state == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
            moveOutToRightAnimation(comic_detail_next_icon).start()
            moveOutToLeftAnimation(comic_detail_previous_icon).start()
        }
    }

    private fun moveOutToRightAnimation(view: View): ObjectAnimator {
        return translationAnimation(view, translationAmount)
    }

    private fun moveOutToLeftAnimation(view: View): ObjectAnimator {
        return translationAnimation(view, -translationAmount)
    }

    private fun translationAnimation(view: View, translation: Float): ObjectAnimator {
        val translateRight = ObjectAnimator.ofFloat(view, "translationX", 0f, translation)
        with(translateRight) {
            duration = 150
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
        }

        return translateRight
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
        setupSwiping()
        comic_detail_next_icon.setOnClickListener { _ -> viewModel.showNextImage() }
        comic_detail_previous_icon.setOnClickListener { _ -> viewModel.showPreviousImage() }
        comic_detail_next_icon.visibility = View.VISIBLE
    }

    private fun setupSwiping() {
        val flingDetector = GestureDetector(this,
            FlingDetector(
                viewModel::showPreviousImage,
                viewModel::showNextImage
            )
        )
        comic_detail_parent_layout.setOnTouchListener { _, event -> flingDetector.onTouchEvent(event) }
    }
}
