package com.example.lucas.spidey2.ui.features.comiclist

import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicListItem
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.ComicPM
import com.example.lucas.spidey2.ui.features.comiclist.adapter.items.LoadingItemPM

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
        }
    }

    private fun mapComics(items: MutableList<ComicListItem>, comics: MutableList<Comic>) {
        items.addAll(comics.map {
            ComicPM(it.id, it.title, it.thumbnailUrl)
        })
    }
}