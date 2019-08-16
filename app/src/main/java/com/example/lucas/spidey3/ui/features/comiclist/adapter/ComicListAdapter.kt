package com.example.lucas.spidey3.ui.features.comiclist.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.lucas.spidey3.R
import com.example.lucas.spidey3.internal.extensions.loadUrl
import com.example.lucas.spidey3.ui.features.comiclist.adapter.items.ComicListItem
import com.example.lucas.spidey3.ui.features.comiclist.adapter.items.ComicPM
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.comic_list_item.*
import kotlinx.android.synthetic.main.error_list_item.*


class ComicListAdapter(private val comicClick: (ComicPM, ImageView) -> Unit,
                       private val retryClick: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: MutableList<ComicListItem> = mutableListOf()

    override fun getItemCount(): Int = items.size

    fun update(newList: List<ComicListItem>) {
        val diffResult = DiffUtil.calculateDiff(ComicListDiff(newList, items))

        this.items.clear()
        this.items.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ComicListItem.Type.COMIC.value -> createComicViewHolder(parent)
            ComicListItem.Type.LOADING_ITEM.value -> createLoadingItemViewHolder(parent)
            ComicListItem.Type.ERROR_ITEM.value -> createErrorItemViewHolder(parent)
            else -> throw RuntimeException("Not supported item type: $viewType")
        }
    }

    private fun createComicViewHolder(parent: ViewGroup): ComicItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comic_list_item, parent, false)
        return ComicItemViewHolder(itemView, comicClick)
    }

    private fun createLoadingItemViewHolder(parent: ViewGroup): LoadingItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.loading_list_item, parent, false)
        return LoadingItemViewHolder(itemView)
    }

    private fun createErrorItemViewHolder(parent: ViewGroup): ErrorItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.error_list_item, parent, false)
        return ErrorItemViewHolder(itemView, retryClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listItem = items.get(holder.adapterPosition)
        when(holder) {
            is ComicItemViewHolder -> holder.bind(listItem as ComicPM)
            is ErrorItemViewHolder -> holder.bind()
        }
    }

    inner class ComicItemViewHolder(override val containerView: View, private val itemClick: (ComicPM, ImageView) -> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(comic: ComicPM) {
            with(comic) {
                comic_title.text = title
                comic_thumbnail.loadUrl(thumbnailUrl)
                caomic_list_item_card_view.setOnClickListener { itemClick.invoke(items[adapterPosition] as ComicPM, comic_thumbnail) }
            }
        }
    }

    inner class LoadingItemViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView)

    inner class ErrorItemViewHolder(override val containerView: View, private val retryClick: () -> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind() {
            comic_list_error_item_card_view.setOnClickListener{ retryClick.invoke() }
        }
    }
}

