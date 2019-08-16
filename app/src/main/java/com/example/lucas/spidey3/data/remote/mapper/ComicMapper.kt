package com.example.lucas.spidey3.data.remote.mapper

import com.example.lucas.spidey3.data.remote.entity.ComicDataWrapperEntity
import com.example.lucas.spidey3.data.remote.entity.ComicImageEntity
import com.example.lucas.spidey3.domain.model.Comic

class ComicMapper {

    fun map(comicDataWrapperEntity: ComicDataWrapperEntity): List<Comic> {
        val comicEntityList = comicDataWrapperEntity.data.results

        if (comicEntityList.isEmpty()) {
            return listOf<Comic>()
        }

        return comicEntityList.map { comicEntity ->
            Comic(comicEntity.id,
                    comicEntity.title,
                    buildImageUrl(comicEntity.thumbnail.path, comicEntity.thumbnail.extension),
                    comicEntity.description ?: "",
                    mapImageEntities(comicEntity.images, buildImageUrl(comicEntity.thumbnail.path, comicEntity.thumbnail.extension))
            )
        }
    }

    private fun mapImageEntities(imageEntities: List<ComicImageEntity>, thumbnailUrl: String): List<String> {
        return imageEntities
                .map { buildImageUrl(it.path,  it.extension) }
                .sortedWith(compareBy { if (it == thumbnailUrl) -1 else 1 })
    }

    private fun buildImageUrl(path: String, extension: String): String {
        return "$path.$extension"
    }
}