package com.example.lucas.spidey3.ui.features.comiclist

import com.example.lucas.spidey3.domain.model.Comic
import com.example.lucas.spidey3.ui.features.comiclist.adapter.items.ComicListItem
import com.example.lucas.spidey3.ui.features.comiclist.adapter.items.ComicPM
import com.example.lucas.spidey3.ui.features.comiclist.adapter.items.ErrorItemPM
import com.example.lucas.spidey3.ui.features.comiclist.adapter.items.LoadingItemPM

class ComicListPresentationMapper {

    fun map(state: ComicListState): List<ComicListItem> {
        val items = mutableListOf<ComicListItem>()

        mapComics(items, state.comics)
        mapStatus(items, state.status)

        return items
    }

    private fun mapStatus(items: MutableList<ComicListItem>, status: ComicListState.Status) {
        if (status == ComicListState.Status.LOADING) {
            items.add(LoadingItemPM())
        } else if (status == ComicListState.Status.ERROR) {
            items.add((ErrorItemPM()))
        }
    }

    private fun mapComics(items: MutableList<ComicListItem>, comics: MutableList<Comic>) {
        items.addAll(comics.map {
            ComicPM(it.id, it.title, it.thumbnailUrl)
        })
    }
}