package com.example.lucas.spidey3.features.comiclist.ui

import com.example.lucas.spidey3.features.common.domain.model.Comic

sealed class ComicListState(
    val comics: List<Comic> = listOf(),
    val loading: Boolean = false,
    val error: Boolean = false
) {
    class Init: ComicListState(emptyList(), loading = true)
    class Loading(state: ComicListState): ComicListState(state.comics, loading = true, error = false)
    class Success(state: ComicListState, loadedComics: List<Comic>):
        ComicListState(state.comics + loadedComics, loading = false, error = false)
    class Error(state: ComicListState): ComicListState(state.comics, loading = false, error = true)

}
