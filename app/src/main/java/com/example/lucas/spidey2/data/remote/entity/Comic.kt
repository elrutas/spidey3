package com.example.lucas.spidey2.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class Comic {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("digitalId")
    @Expose
    var digitalId: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("issueNumber")
    @Expose
    var issueNumber: String? = null
    @SerializedName("variantDescription")
    @Expose
    var variantDescription: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("modified")
    @Expose
    var modified: String? = null
    @SerializedName("isbn")
    @Expose
    var isbn: String? = null
    @SerializedName("upc")
    @Expose
    var upc: String? = null
    @SerializedName("diamondCode")
    @Expose
    var diamondCode: String? = null
    @SerializedName("ean")
    @Expose
    var ean: String? = null
    @SerializedName("issn")
    @Expose
    var issn: String? = null
    @SerializedName("format")
    @Expose
    var format: String? = null
    @SerializedName("pageCount")
    @Expose
    var pageCount: String? = null
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null
    @SerializedName("images")
    @Expose
    var images: List<ComicImage> = ArrayList()
}

