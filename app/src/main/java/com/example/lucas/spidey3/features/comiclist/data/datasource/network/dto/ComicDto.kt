package com.example.lucas.spidey3.features.comiclist.data.datasource.network.dto

class ComicDto (
    var id: Int,
    var digitalId: String,
    var title: String,
    var issueNumber: String,
    var variantDescription: String,
    var description: String?,
    var modified: String,
    var isbn: String,
    var upc: String,
    var diamondCode: String,
    var ean: String,
    var issn: String,
    var format: String,
    var pageCount: String,
    var resourceURI: String,
    var thumbnail: ThumbnailDto,
    var images: List<ComicImageDto>
)

