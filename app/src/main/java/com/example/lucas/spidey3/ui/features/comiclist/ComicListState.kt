package com.example.lucas.spidey3.ui.features.comiclist

import com.example.lucas.spidey3.domain.model.Comic

class ComicListState {
    var status: Status = Status.IDLE
    var comics = mutableListOf<Comic>()

    enum class Status {
        IDLE, LOADING, ERROR, NEEDS_LOAD
    }
}
