package com.durbar.bangabandhuplay.data.model.get_related_contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SingleContentRelatedContent(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("uuid")
    val uuid: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("short_title")
    val shortTitle: Any? = null,

    @field:SerializedName("access")
    val access: String? = null,

    @field:SerializedName("youtube_url")
    val youtubeUrl: Any? = null,

    @field:SerializedName("cloud_url")
    val cloudUrl: Any? = null,

    @field:SerializedName("poster")
    val poster: Any? = null,

    @field:SerializedName("view_count")
    val viewCount: Any? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("order")
    val order: Int? = 0,

    @field:SerializedName("runtime")
    val runtime: Any? = null,

    @field:SerializedName("content_source")
    val contentSource: List<ContentSource>? = listOf(),

    @field:SerializedName("average_review_count")
    val averageReviewCount: Int? = 0,

    @field:SerializedName("thumbnail_portrait")
    val thumbnailPortrait: String? = null,

    @field:SerializedName("thumbnail_landscape")
    val thumbnailLandscape: String? = null
)
