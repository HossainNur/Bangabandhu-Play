package live.durbar.bangabandhuapp.data.model.category.single_sub

import com.google.gson.annotations.SerializedName

data class OttContent(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("uuid")
    val uuid: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("short_title")
    val shortTitle: String? = null,

    @field:SerializedName("root_category_id")
    val rootCategoryId: Int? = 0,

    @field:SerializedName("sub_category_id")
    val subCategoryId: Int? = 0,

    @field:SerializedName("sub_sub_category_id")
    val subSubCategoryId: String? = null,

    @field:SerializedName("series_id")
    val seriesId: String? = null,

    @field:SerializedName("content_type_id")
    val contentTypeId: String? = null,

    @field:SerializedName("description")
    val description: Object? = null,

    @field:SerializedName("bangla_description")
    val banglaDescription: String? = null,

    @field:SerializedName("year")
    val year: String? = null,

    @field:SerializedName("runtime")
    val runtime: String? = null,

    @field:SerializedName("youtube_url")
    val youtubeUrl: String? = null,

    @field:SerializedName("cloud_url")
    val cloudUrl: String? = null,

    @field:SerializedName("intro_starts")
    val introStarts: String? = null,

    @field:SerializedName("intro_end")
    val introEnd: String? = null,

    @field:SerializedName("next_end")
    val nextEnd: String? = null,

    @field:SerializedName("poster")
    val poster: String? = null,

    @field:SerializedName("backdrop")
    val backdrop: String? = null,

    @field:SerializedName("thumbnail_portrait")
    val thumbnailPortrait: String? = null,

    @field:SerializedName("thumbnail_landscape")
    val thumbnailLandscape: String? = null,

    @field:SerializedName("tv_cover")
    val tvCover: String? = null,

    @field:SerializedName("view_count")
    val viewCount: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("access")
    val access: String? = null,

    @field:SerializedName("order")
    val order: Int? = 0,

    @field:SerializedName("series_order")
    val seriesOrder: Int? = 0,

    @field:SerializedName("number_of_allowed_audience_per_user")
    val numberOfAllowedAudiencePerUser: Int? = 0,

    @field:SerializedName("title_bangla")
    val titleBangla: String? = null,

    @field:SerializedName("content_type")
    val contentType: String? = null,

    @field:SerializedName("vod_type")
    val vodType: String? = null,

    @field:SerializedName("video_type")
    val videoType: String? = null,

    @field:SerializedName("upload_date")
    val uploadDate: String? = null,

    @field:SerializedName("imdb")
    val imdb: String? = null,

    @field:SerializedName("saga")
    val saga: Any? = null,

    @field:SerializedName("is_original")
    val isOriginal: String? = null,

    @field:SerializedName("synopsis_english")
    val synopsisEnglish: Any? = null,

    @field:SerializedName("synopsis_bangla")
    val synopsisBangla: Any? = null,

    @field:SerializedName("genre")
    val genre: String? = null,

    @field:SerializedName("tags")
    val tags: String? = null,

    @field:SerializedName("associated_teaser")
    val associatedTeaser: Any? = null,

    @field:SerializedName("up_comming")
    val upComming: Any? = null,

    @field:SerializedName("content_owner_id")
    val contentOwnerId: Int? = 0,

    @field:SerializedName("external_id")
    val externalId: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null
)