package com.example.lucas.spidey3.features.common.data.network.dto

class ComicDataWrapperDto(
    var code: String,
    var status: String,
    var copyright: String,
    var attributionText: String,
    var attributionHTML: String,
    var data: ComicDataContainerDto,
    var etag: String
)