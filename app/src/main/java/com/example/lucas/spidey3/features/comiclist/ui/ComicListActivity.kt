package com.example.lucas.spidey3.features.comiclist.ui

import android.os.Bundle
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
import kotlin.math.roundToInt


class ComicListActivity : BaseActivity() {

    private val viewModel: ComicListViewModel by lazy { viewModel(ComicListViewModel::class.java) }
    private val presentationMapper = ComicListPresentationMapper()

    lateinit var comicListAdapter: ComicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_list)

        Injector.getAppComponent().inject(this)

        setupComicList()

        viewModel.liveState().observe(this, Observer { onStateChanged(it) })
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
        val itemWidth = resources.getDimension(R.dimen.span_width)
        return (dpWidth / itemWidth).roundToInt()
    }

    fun showComics(items: List<ComicListItem>) {
        comicListAdapter.update(items)
    }

    private fun launchDetailActivity(comicPM: ComicPM, imageViewForAnimation: ImageView) {
        ComicDetailActivity.launchDetailActivity(this, comicPM.id, comicPM.title, comicPM.thumbnailUrl,
            imageViewForAnimation)
    }
}
