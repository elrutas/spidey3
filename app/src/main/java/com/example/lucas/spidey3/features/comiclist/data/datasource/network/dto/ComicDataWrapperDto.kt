package com.example.lucas.spidey3.features.comiclist.data.datasource.network.dto

class ComicDataWrapperDto(
    var code: String,
    var status: String,
    var copyright: String,
    var attributionText: String,
    var attributionHTML: String,
    var data: ComicDataContainerDto,
    var etag: String
)