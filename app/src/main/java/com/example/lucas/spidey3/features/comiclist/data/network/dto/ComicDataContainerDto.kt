package com.example.lucas.spidey3.features.comiclist.data.network.dto

class ComicDataContainerDto(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ComicDto>
)
