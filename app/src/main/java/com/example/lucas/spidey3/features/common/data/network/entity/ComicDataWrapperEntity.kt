package com.example.lucas.spidey3.features.common.data.network.entity

class ComicDataWrapperEntity(
        var code: String,
        var status: String,
        var copyright: String,
        var attributionText: String,
        var attributionHTML: String,
        var data: ComicDataContainerEntity,
        var etag: String
)