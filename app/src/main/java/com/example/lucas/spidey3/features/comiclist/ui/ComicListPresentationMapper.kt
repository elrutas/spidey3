package com.example.lucas.spidey3.features.comiclist.ui

import com.example.lucas.spidey3.features.comicdetail.domain.model.Comic
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicListItem
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ComicPM
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.ErrorItemPM
import com.example.lucas.spidey3.features.comiclist.ui.adapter.items.LoadingItemPM

class ComicListPresentationMapper {

    fun map(state: ComicListState): List<ComicListItem> {
        val items = mutableListOf<ComicListItem>()

        mapComics(state.comics, items)

        if (state.loading) {
            items.add(LoadingItemPM())
        }

        if (state.error) {
            items.add(ErrorItemPM())
        }

        return items
    }

    private fun mapComics(comics: List<Comic>, items: MutableList<ComicListItem>) {
        items.addAll(comics.map {
            ComicPM(it.id, it.title, it.thumbnailUrl)
        })
    }
}