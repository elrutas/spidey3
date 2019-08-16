package com.example.lucas.spidey3.features.comiclist.ui.adapter.items

open class ComicListItem(val type: Type) {

    enum class Type(val value: Int) {
        COMIC(0),
        LOADING_ITEM(1),
        ERROR_ITEM(2)
    }
}