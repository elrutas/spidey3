package com.example.lucas.spidey3.features.comiclist.ui

import com.example.lucas.spidey3.features.comicdetail.domain.model.Comic

class ComicListState {
    var status: Status =
        Status.IDLE
    var comics = mutableListOf<Comic>()

    enum class Status {
        IDLE, LOADING, ERROR
    }
}
