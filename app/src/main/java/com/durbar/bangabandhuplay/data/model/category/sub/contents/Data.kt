package com.durbar.bangabandhuplay.data.model.category.sub.contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Data(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("uuid")
    val uuid: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("short_title")
    val shortTitle: String? = null,

    @field:SerializedName("youtube_url")
    val youtubeUrl: String? = null,

    @field:SerializedName("cloud_url")
    val cloudUrl: Any? = null,

    @field:SerializedName("poster")
    val poster: String? = null,

    @field:SerializedName("view_count")
    val viewCount: Int? = 0,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("order")
    val order: Int? = 0
)