package com.example.lucas.spidey2.ui.features.comiclist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lucas.spidey2.R
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.comic_list_item.*
import android.support.v7.util.DiffUtil


class ComicListAdapter(val itemClick: (Comic) -> Unit) : RecyclerView.Adapter<ComicListAdapter.ComicItemViewHolder>() {

    val comicList: MutableList<Comic> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comic_list_item, parent, false)
        return ComicItemViewHolder(itemView, itemClick)
    }

    override fun getItemCount(): Int = comicList.size

    override fun onBindViewHolder(holder: ComicItemViewHolder, position: Int) {
        val comic = comicList.get(holder.adapterPosition)
        holder.bind(comic)
    }

    fun addComics(newList: List<Comic>) {
        val diffResult = DiffUtil.calculateDiff(ComicListDiff(comicList, newList))

        this.comicList.clear()
        this.comicList.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    inner class ComicItemViewHolder(override val containerView: View, private val itemClick: (Comic) -> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(comic: Comic) {
            with(comic) {
                comic_title.text = title
                comic_thumbnail.loadUrl(thumbnailUrl)
                card_view.setOnClickListener { itemClick.invoke(comicList[adapterPosition]) }
            }
        }
    }
}

