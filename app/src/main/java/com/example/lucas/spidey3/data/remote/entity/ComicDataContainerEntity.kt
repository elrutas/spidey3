package com.example.lucas.spidey3.data.remote.entity

class ComicDataContainerEntity(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ComicEntity>
)
