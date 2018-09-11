package com.example.lucas.spidey2.internal.utils.testing

import com.example.lucas.spidey2.domain.model.Comic

class ComicMother {
    companion object {

        fun aComic() : Comic {
            return Comic(1,
                    "Shiny Title",
                    "http://i.annihil.us/u/prod/marvel/i/mg/6/03/5a724221d7369.jpg",
                    "This comic is awesome",
                    listOf("http://i.annihil.us/u/prod/marvel/i/mg/6/03/5a724221d7369.jpg")
            )
        }
    }
}
