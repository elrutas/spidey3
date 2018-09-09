package com.example.lucas.spidey2.data.remote.mapper

import com.example.lucas.spidey2.data.remote.entity.ComicDataWrapperEntity
import com.example.lucas.spidey2.data.remote.entity.ComicImageEntity
import com.example.lucas.spidey2.domain.model.Comic

class ComicMapper {

    fun map(comicDataWrapperEntity: ComicDataWrapperEntity): List<Comic> {
        val comicEntityList = comicDataWrapperEntity.dataEntity?.comicEntityList

        if (comicEntityList == null || comicEntityList.isEmpty()) {
            return listOf<Comic>()
        }

        return comicEntityList.map { comicEntity ->
            Comic(comicEntity.title ?: "",
                    comicEntity.thumbnailEntity?.path + "." + comicEntity.thumbnailEntity?.extension,
                    comicEntity.description ?: "",
                    mapImageEntities(comicEntity.imageEntities)
            )
        }
    }

    private fun mapImageEntities(imageEntities: List<ComicImageEntity>): List<String> {
        return imageEntities.map {
            it.path + "." + it.extension
        }

    }
}