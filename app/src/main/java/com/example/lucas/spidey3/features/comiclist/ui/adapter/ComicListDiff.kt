package com.example.lucas.spidey3.features.comiclist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicListItem
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicPM
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ErrorItemPM
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.LoadingItemPM

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
                is ErrorItemPM -> true
                else -> false
            }
        }

        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}