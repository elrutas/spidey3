package com.example.lucas.spidey2.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ComicImageEntity {
    @SerializedName("path")
    @Expose
    var path: String? = null
    @SerializedName("extension")
    @Expose
    var extension: String? = null

}
