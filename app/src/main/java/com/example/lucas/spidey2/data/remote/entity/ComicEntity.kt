package com.example.lucas.spidey2.data.remote.entity

class ComicEntity (
        var id: String,
        var digitalId: String,
        var title: String,
        var issueNumber: String,
        var variantDescription: String,
        var description: String,
        var modified: String,
        var isbn: String,
        var upc: String,
        var diamondCode: String,
        var ean: String,
        var issn: String,
        var format: String,
        var pageCount: String,
        var resourceURI: String,
        var thumbnail: ThumbnailEntity,
        var images: List<ComicImageEntity>
)

