package com.example.lucas.spidey3.features.common.domain.model

data class Comic(val id: Int,
                 val title: String,
                 val thumbnailUrl: String,
                 val description: String,
                 val imageUrls: List<String>)