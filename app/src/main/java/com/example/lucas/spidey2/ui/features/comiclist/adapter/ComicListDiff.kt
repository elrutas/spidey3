package com.example.lucas.spidey2.ui.features.comiclist.adapter

import android.support.v7.util.DiffUtil
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicListItem
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicPM
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.LoadingItemPM

class ComicListDiff(val newItems: List<ComicListItem>, val oldItems: List<ComicListItem>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size
    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        if (newItem.type == oldItem.type) {
            return when(oldItem) {
                is ComicPM -> oldItem.id == (newItem as ComicPM).id
                is LoadingItemPM -> true
                else -> false
            }
        }

        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}