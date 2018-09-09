package com.example.lucas.spidey2.domain.model

data class Comic(val id: String,
                 val title: String,
                 val thumbnailUrl: String,
                 val description: String,
                 val imageUrls: List<String>)