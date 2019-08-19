package com.example.lucas.spidey3.features.comiclist.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.example.lucas.spidey3.R
import com.example.lucas.spidey3.internal.di.Injector
import com.example.lucas.spidey3.features.comicdetail.ui.ComicDetailActivity
import com.example.lucas.spidey3.features.comiclist.ui.adapter.ComicListAdapter
import com.example.lucas.spidey3.features.comiclist.ui.adapter.EndlessRecyclerViewScrollListener
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicListItem
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicPM
import com.example.lucas.spidey3.features.common.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_comic_list.*


class ComicListActivity : BaseActivity() {
    private val viewModel: ComicListViewModel by lazy { viewModel(ComicListViewModel::class.java) }

    val presentationMapper = ComicListPresentationMapper()

    lateinit var comicListAdapter: ComicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_list)

        Injector.getAppComponent().inject(this)

        setupComicList()
        viewModel.liveState().observe(this, Observer { onStateChanged(it) })

        viewModel.loadComics()
    }

    private fun onStateChanged(state: ComicListState) {
        showComics(presentationMapper.map(state))
    }

    private fun setupComicList() {
        comicListAdapter = ComicListAdapter(::onComicClicked, viewModel::loadComics)

        comic_list_recycler.setHasFixedSize(false)
        comic_list_recycler.layoutManager = StaggeredGridLayoutManager(
            calculateSpanCount(),
            RecyclerView.VERTICAL
        )
        comic_list_recycler.adapter = comicListAdapter
        (comic_list_recycler.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        comic_list_recycler.addOnScrollListener(object : EndlessRecyclerViewScrollListener(comic_list_recycler.layoutManager as StaggeredGridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                viewModel.loadComics()
            }
        })
    }

    private fun onComicClicked(comic: ComicPM, comicThumbnail: ImageView) {
        launchDetailActivity(comic, comicThumbnail)
    }

    private fun calculateSpanCount(): Int {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val dpWidth = outMetrics.widthPixels.toFloat()
        val itemWidth = getResources().getDimension(R.dimen.span_width)
        return Math.round(dpWidth / itemWidth)
    }

    fun showComics(items: List<ComicListItem>) {
        comicListAdapter.update(items)
    }

    fun launchDetailActivity(comicPM: ComicPM, imageViewForAnimation: ImageView) {
        val intent = Intent(applicationContext, ComicDetailActivity::class.java)
        intent.putExtra(ComicDetailActivity.COMIC_ID_EXTRA, comicPM.id)
        intent.putExtra(ComicDetailActivity.COMIC_TITLE_EXTRA, comicPM.title)
        intent.putExtra(ComicDetailActivity.COMIC_THUMBNAIL, comicPM.thumbnailUrl)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageViewForAnimation, getString(R.string.comic_thumbnail_transition))
        startActivity(intent, options.toBundle())
    }
}
