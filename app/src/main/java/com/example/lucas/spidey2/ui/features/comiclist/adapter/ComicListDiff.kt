package com.example.lucas.spidey2.ui.features.comiclist.adapter

import android.support.v7.util.DiffUtil
import com.example.lucas.spidey2.domain.model.Comic

class ComicListDiff(val newComics: List<Comic>, val oldComics: List<Comic>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldComics.size

    override fun getNewListSize() = newComics.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldComics[oldItemPosition].id == newComics[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldComics[oldItemPosition] == newComics[newItemPosition]
    }

}