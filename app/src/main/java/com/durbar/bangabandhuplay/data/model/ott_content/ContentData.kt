package com.durbar.bangabandhuplay.data.model.ott_content

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentData(
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("uuid")
    val uuid: String? = null,
    @field:SerializedName("access")
    val access: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("short_title")
    val shortTitle: Any? = null,
    @field:SerializedName("root_category_id")
    val rootCategoryId: Int? = 0,
    @field:SerializedName("sub_category_id")
    val subCategoryId: Any? = null,
    @field:SerializedName("sub_sub_category_id")
    val subSubCategoryId: Any? = null,
    @field:SerializedName("series_id")
    val seriesId: Any? = null,
    @field:SerializedName("content_type_id")
    val contentTypeId: Any? = null,
    @field:SerializedName("description")
    val description: Any? = null,
    @field:SerializedName("year")
    val year: String? = null,
    @field:SerializedName("runtime")
    val runtime: String? = null,
    @field:SerializedName("youtube_url")
    val youtubeUrl: Any? = null,
    @field:SerializedName("cloud_url")
    val cloudUrl: Any? = null,
    @field:SerializedName("poster")
    val poster: Any? = null,
    @field:SerializedName("backdrop")
    val backdrop: Any? = null,
    @field:SerializedName("view_count")
    val viewCount: Any? = null,
    @field:SerializedName("release_date")
    val releaseDate: String? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("order")
    val order: Int? = 0,
    @field:SerializedName("content_meta")
    val contentMeta: List<Any>? = listOf(),
    @field:SerializedName("series_info")
    val seriesInfo: Any? = null,
    @field:SerializedName("content_source")
    val contentSource: List<ContentSource>? = listOf(),
    @field:SerializedName("average_review_count")
    val averageReviewCount: Int? = 0,
    @field:SerializedName("reviews")
    val reviews: List<Any>? = listOf(),
    @field:SerializedName("cast_and_crews")
    val castAndCrews: List<CastAndCrew>? = listOf(),
    @field:SerializedName("synopsis_english")
    val synopsisEnglish: String? = null,
    @field:SerializedName("synopsis_bangla")
    val synopsisBangla: Any? = null,
    @field:SerializedName("thumbnail_portrait")
    val thumbnailPortrait: String? = null,
    @field:SerializedName("thumbnail_landscape")
    val thumbnailLandscape: String? = null,
    @field:SerializedName("imdb")
    val imdb: String? = null,
    @field:SerializedName("saga")
    val saga: String? = null,
    @field:SerializedName("genre")
    val genre: String? = null,
    @field:SerializedName("is_original")
    val isOriginal: String? = null,
    @field:SerializedName("video_type")
    val videoType: String? = null
)
