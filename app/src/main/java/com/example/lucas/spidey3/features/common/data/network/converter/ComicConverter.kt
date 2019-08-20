package com.example.lucas.spidey3.features.common.data.network.converter

import com.example.lucas.spidey3.features.common.data.network.dto.ComicDataWrapperDto
import com.example.lucas.spidey3.features.common.data.network.dto.ComicImageDto
import com.example.lucas.spidey3.features.common.domain.model.Comic

class ComicConverter {

    fun map(comicDataWrapperDto: ComicDataWrapperDto): List<Comic> {
        val comicEntityList = comicDataWrapperDto.data.results

        if (comicEntityList.isEmpty()) {
            return listOf()
        }

        return comicEntityList.map { comicEntity ->
            Comic(
                comicEntity.id,
                comicEntity.title,
                buildImageUrl(comicEntity.thumbnail.path, comicEntity.thumbnail.extension),
                comicEntity.description ?: "",
                mapImageEntities(
                    comicEntity.images,
                    buildImageUrl(comicEntity.thumbnail.path, comicEntity.thumbnail.extension)
                )
            )
        }
    }

    private fun mapImageEntities(imageDtos: List<ComicImageDto>, thumbnailUrl: String): List<String> {
        return imageDtos
                .map { buildImageUrl(it.path,  it.extension) }
                .sortedWith(compareBy { if (it == thumbnailUrl) -1 else 1 })
    }

    private fun buildImageUrl(path: String, extension: String): String {
        return "$path.$extension".replace("http", "https")
    }
}